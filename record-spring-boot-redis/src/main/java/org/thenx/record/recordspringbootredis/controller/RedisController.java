package org.thenx.record.recordspringbootredis.controller;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordspringbootredis.service.RedisService;

/**
 * @author May
 * <p>
 * redis 测试接口
 */
@RestController
public class RedisController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Redis 接口测试
     *
     * @param id id
     * @return str
     */
    @GetMapping(value = "/test")
    public String redis(@RequestParam("id") Integer id) {
        RedisService redisService = p -> "Redis 接口测试：" + p;
        return redisService.resp(id);
    }
}
