package top.xiaorang.redis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liulei
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
  private static final Logger LOG = LoggerFactory.getLogger(MyMetaObjectHandler.class);

  @Override
  public void insertFill(MetaObject metaObject) {
    LOG.info("start insert fill ....");
    this.strictInsertFill(metaObject, "createTime", Date::new, Date.class);
    this.strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    LOG.info("start update fill ....");
    this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
  }
}
