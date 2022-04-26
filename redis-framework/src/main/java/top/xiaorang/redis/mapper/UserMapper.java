package top.xiaorang.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xiaorang.redis.entity.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaorang
 * @since 2022-04-26 12:37:41
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
