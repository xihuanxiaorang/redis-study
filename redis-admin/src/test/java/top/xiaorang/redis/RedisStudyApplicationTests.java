package top.xiaorang.redis;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.xiaorang.redis.entity.User;
import top.xiaorang.redis.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class RedisStudyApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    @Transactional(rollbackFor = Exception.class) // 导致回滚
    public void save() {
        User user = User.builder().username("小让").password("123456").sex(1).build();
        userService.save(user);
    }

    @Test
    public void list() {
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }

    @Test
    public void addUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = User.builder().username("zzyy" + i)
                    .sex(new Random().nextInt(2))
                    .password(IdUtil.simpleUUID().substring(0, 6)).build();
            users.add(user);
        }
        userService.saveBatch(users);
    }
}
