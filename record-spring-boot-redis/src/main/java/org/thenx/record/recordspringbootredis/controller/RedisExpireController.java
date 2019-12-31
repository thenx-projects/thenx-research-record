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
 * Redis 插入时附带过期时间
 */
@RestController
public class RedisExpireController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 删除接口测试
     *
     * @param i id
     * @return str
     */
    @GetMapping(value = "/setExpire")
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
            redisTemplate.opsForValue().setIfAbsent(p, "插入一个带过期30秒时间的值", 30, TimeUnit.SECONDS);
            logger.info("插入了附带时间的：" + redisTemplate.opsForValue().get(p));
            return "插入了附带时间的：" + redisTemplate.opsForValue().get(p);
        };
        return redisService.resp(id);
    }
}
