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
 * <p>
 * Redis 删除操作
 */
@RestController
public class RedisDelController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 删除接口测试
     *
     * @param i id
     * @return str
     */
    @GetMapping(value = "/del")
    public String redis(@RequestParam("id") Integer i) {

        String id;
        if (i == null) {
            throw new RuntimeException("参数没有传对: " + i);
        } else {
            id = i + "";
        }

        RedisService redisService = null;
        // Redis 删除操作
        redisService = p -> {
            String param = (String) redisTemplate.opsForValue().get(p);
            if (param != null) {
                redisTemplate.delete(param);
                if (redisTemplate.opsForValue().get(p) == null) {
                    logger.info("\n -----> 数据删除成功");
                } else {
                    logger.info("\n -----> 删除失败：" + redisTemplate.opsForValue().get(p));
                }
                return "删除数据 Redis: " + Objects.requireNonNull(redisTemplate.opsForValue().get(p));
            } else {
                return "删除失败";
            }
        };
        return redisService.resp(id);
    }
}
