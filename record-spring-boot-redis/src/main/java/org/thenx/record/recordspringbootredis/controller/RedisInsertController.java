package org.thenx.record.recordspringbootredis.controller;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordspringbootredis.service.RedisService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author May
 * <p>
 * redis 增加测试接口
 */
@RestController
public class RedisInsertController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 增加接口测试
     *
     * @param i id
     * @return str
     */
    @GetMapping(value = "/insert")
    public String redis(@RequestParam("id") Integer i) {

        String id = "";
        if (i == null) {
            throw new RuntimeException("参数没有传对: " + i);
        } else {
            id = i + "";
        }

        RedisService redisService = null;
        // Redis 增加操作
        redisService = p -> {
            String param = (String) redisTemplate.opsForValue().get(p);
            if (param == null) {
                redisTemplate.opsForValue().set(p, "插入数据一条：" + p);
                logger.info("\n -----> 插入成功");
                return "插入一条到 Redis: " + Objects.requireNonNull(redisTemplate.opsForValue().get(p)).toString();
            } else {
                return "数据为空";
            }
        };
        return redisService.resp(id);
    }
}
