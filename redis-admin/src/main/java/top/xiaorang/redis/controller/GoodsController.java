package top.xiaorang.redis.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liulei
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final StringRedisTemplate redisTemplate;
    @Value("${sever.port}")
    private String serverPort;
    private final Redisson redisson;

    public GoodsController(StringRedisTemplate redisTemplate, Redisson redisson) {
        this.redisTemplate = redisTemplate;
        this.redisson = redisson;
    }

    @GetMapping("/buy")
    public String buyGoods() {
        String key = "lock";
        RLock redissonLock = redisson.getLock(key);
        redissonLock.lock();
        try {
            String res = redisTemplate.opsForValue().get("goods:001");
            int goodsNumber = res == null ? 0 : Integer.parseInt(res);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口：" + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口：" + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口：" + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口：" + serverPort;
        } finally {
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
    }
}
