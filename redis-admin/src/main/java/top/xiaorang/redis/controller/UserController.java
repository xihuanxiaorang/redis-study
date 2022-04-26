package top.xiaorang.redis.controller;


import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.*;
import top.xiaorang.redis.dto.UserDTO;
import top.xiaorang.redis.entity.User;
import top.xiaorang.redis.mapstruct.UserMapping;
import top.xiaorang.redis.service.UserService;

import java.util.Random;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xiaorang
 * @since 2022-04-26 12:41:01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser() {
        for (int i = 0; i < 5; i++) {
            User user = User.builder().username("zzyy" + i)
                    .sex(new Random().nextInt(2))
                    .password(IdUtil.simpleUUID().substring(0, 6)).build();
            userService.addUser(user);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public void updateUser(UserDTO userDTO) {
        userService.updateUser(UserMapping.INSTANCE.userDtoToUser(userDTO));
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }
}
