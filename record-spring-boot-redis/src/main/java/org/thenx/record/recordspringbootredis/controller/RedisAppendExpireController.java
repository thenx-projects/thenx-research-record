package org.thenx.record.recordspringbootredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordspringbootredis.service.RedisService;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author May
 * <p>
 * 更新过期时间
 */
@RestController
public class RedisAppendExpireController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 延长过期时间接口测试
     *
     * @param i id
     * @return str
     */
    @GetMapping(value = "/appendExpire")
    public String redis(@RequestParam("id") Integer i) {

        String id;
        if (i == null) {
            throw new RuntimeException("参数没有传对: " + i);
        } else {
            id = i + "";
        }

        RedisService redisService = null;
        // Redis 附带过期时间的增加操作
        redisService = p -> {
            String param = (String) redisTemplate.opsForValue().get(p);
            if (param != null) {
                redisTemplate.expire(param, 10, TimeUnit.MINUTES);
                logger.info("延长时间：" + redisTemplate.opsForValue().get(param));
            }
            return "延长时间：" + redisTemplate.opsForValue().get(Objects.requireNonNull(param));
        };
        return redisService.resp(id);
    }
}
