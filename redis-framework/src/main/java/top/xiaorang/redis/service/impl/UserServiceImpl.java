package top.xiaorang.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.xiaorang.redis.entity.User;
import top.xiaorang.redis.mapper.UserMapper;
import top.xiaorang.redis.service.UserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaorang
 * @since 2022-04-26 12:37:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
