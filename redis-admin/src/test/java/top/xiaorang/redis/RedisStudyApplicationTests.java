package top.xiaorang.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.xiaorang.redis.entity.User;
import top.xiaorang.redis.service.UserService;

import java.util.List;

@SpringBootTest
public class RedisStudyApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = User.builder().username("小让").password("123456").sex(1).build();
        userService.save(user);
    }

    @Test
    public void list() {
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }
}
