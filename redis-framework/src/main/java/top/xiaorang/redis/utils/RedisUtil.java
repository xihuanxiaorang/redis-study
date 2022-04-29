package top.xiaorang.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liulei
 */
public class RedisUtil {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, "120.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        return null;
    }
}
