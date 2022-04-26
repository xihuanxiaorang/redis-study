package top.xiaorang.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xiaorang.redis.entity.User;
import top.xiaorang.redis.mapper.UserMapper;
import top.xiaorang.redis.service.UserService;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaorang
 * @since 2022-04-26 12:37:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String CACHE_USER_KEY = "user:";
    private final UserMapper userMapper;
    private final RedisTemplate<String, Serializable> redisTemplate;

    public UserServiceImpl(UserMapper userMapper, RedisTemplate<String, Serializable> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addUser(User user) {
        // 插入用户数据
        int num = userMapper.insert(user);
        if (num > 0) {
            // 查询数据库，将插入进去的数据重新读取回来，保证数据正确
            user = userMapper.selectById(user.getId());
            // 将查询出来的数据存入redis中
            redisTemplate.opsForValue().set(CACHE_USER_KEY + user.getId(), user);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        int num = userMapper.deleteById(id);
        if (num > 0) {
            redisTemplate.delete(CACHE_USER_KEY + id);
        }
    }

    @Override
    public void updateUser(User user) {
        int num = userMapper.updateById(user);
        if (num > 0) {
            user = userMapper.selectById(user.getId());
            redisTemplate.opsForValue().set(CACHE_USER_KEY + user.getId(), user);
        }
    }

    @Override
    public User findUserById(Integer id) {
        String key = CACHE_USER_KEY + id;
        // 先从redis中查询，查到了直接返回，否则查询数据库
        User user = (User) redisTemplate.opsForValue().get(key);
        if (user == null) {
            // 大厂用，对于高QPS，进来就加锁，保证一个请求操作
            synchronized (UserServiceImpl.class) {
                // 再次从redis中查询，查到了直接返回，否则查询数据库
                user = (User) redisTemplate.opsForValue().get(key);
                if (user == null) {
                    // 查询数据库
                    user = userMapper.selectById(id);
                    if (user != null) {
                        // 如果数据库中有数据，则需要将查询到的数据回写到redis,完成数据一致性的同步工作
                        redisTemplate.opsForValue().set(key, user);
                    }
                }
            }
        }
        return user;
    }
}
