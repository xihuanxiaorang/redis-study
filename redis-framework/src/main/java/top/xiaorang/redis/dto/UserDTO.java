package top.xiaorang.redis.dto;

import lombok.Data;

/**
 * @author liulei
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
}
