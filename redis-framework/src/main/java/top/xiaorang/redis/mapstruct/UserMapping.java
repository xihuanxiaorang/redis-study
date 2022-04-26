package top.xiaorang.redis.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xiaorang.redis.dto.UserDTO;
import top.xiaorang.redis.entity.User;

/**
 * @author liulei
 */
@Mapper
public interface UserMapping {
    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    /**
     * 将用户DTO对象转换成用户对象
     *
     * @param userDTO 用户DTO
     * @return 用户对象
     */
    User userDtoToUser(UserDTO userDTO);
}
