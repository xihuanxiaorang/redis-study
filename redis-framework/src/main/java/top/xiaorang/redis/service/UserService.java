package top.xiaorang.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xiaorang.redis.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiaorang
 * @since 2022-04-26 12:37:41
 */
public interface UserService extends IService<User> {
    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
    void addUser(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void deleteUser(Integer id);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 通过用户id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findUserById(Integer id);
}
