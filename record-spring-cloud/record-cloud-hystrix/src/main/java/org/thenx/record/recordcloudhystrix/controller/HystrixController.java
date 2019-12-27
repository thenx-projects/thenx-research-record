package org.thenx.record.recordcloudhystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 * <p>
 * 熔断控制方法
 */
@RestController
public class HystrixController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 正常调用方法
     *
     * @return string
     */
    @HystrixCommand(fallbackMethod = "rollback")
    @GetMapping("/nm")
    public String normalMethod() {
        return "正常调用Hystrix方法";
    }

    /**
     * 出发熔断机制回调 rollback 方法
     *
     * @return string
     */
    public String rollback() {
        return "触发熔断机制回调 rollback 方法";
    }
}
