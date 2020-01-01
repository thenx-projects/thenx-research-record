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

/**
 * @author May
 *
 * Redis 更新接口测试
 */
@RestController
public class RedisUpdateController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 修改接口测试
     *
     * @param i id
     * @return str
     */
    @GetMapping(value = "/update")
    public String redis(@RequestParam("id") Integer i) {

        String id;
        if (i == null) {
            throw new RuntimeException("参数没有传对: " + i);
        } else {
            id = i + "";
        }

        RedisService redisService = null;
        // Redis 更新操作
        redisService = p -> {
            String param = (String) redisTemplate.opsForValue().get(p);
            if (param != null) {
                redisTemplate.opsForValue().set(p, "更新数据一条：" + p + "1");
                logger.info("\n -----> 原数据: " + param + " &现在数据：" + redisTemplate.opsForValue().get(p));
                return "更新一条到数据到 Redis: " + Objects.requireNonNull(redisTemplate.opsForValue().get(p)).toString();
            } else {
                return "更新数据为空";
            }
        };
        return redisService.resp(id);
    }
}
