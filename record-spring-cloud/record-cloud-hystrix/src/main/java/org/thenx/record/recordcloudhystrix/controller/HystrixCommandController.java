package org.thenx.record.recordcloudhystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordcloudhystrix.service.EurekaClientService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * 熔断机制更进一步的方法
 */
@RestController
public class HystrixCommandController {

    @Resource
    private EurekaClientService eurekaClientService;

    /**
     * 开启熔断，注解中有以下几个注解
     * <p>
     * 1. HystrixProperty(name = "circuitBreaker.enabled", value = "true")
     * 这个代表开启熔断机制
     * <p>
     * 2. HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "true")
     * 这个代表触发熔断机制时间，默认为20
     * 如果设为20，那么当一个rolling window(统计时间段,默认10s)的时间内收到19个请求，即使19个请求都失败，也不会触发circuit break(熔断)
     * <p>
     * 3. HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "8000")
     * 这个代表触发短路的时间值，当该值设为5000时，则当触发circuit break后的5000毫秒内都会拒绝request
     * 也就是5000毫秒后才会关闭circuit(断路器)。默认5000
     * <p>
     * 4. @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
     * 这个代表错误比率阀值，如果错误率>=该值，circuit(断路器)会被打开，并短路所有请求触发fallback。默认50
     *
     * @return string
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "8000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
    }, fallbackMethod = "rollback")
    @GetMapping(value = "/command")
    public String result(String name) {
        if ("1".equals(name)) {
            return "正常调用";
        }
        return eurekaClientService.ec();
    }

    /**
     * 触发熔断降级机制
     *
     * @return string
     */
    public String rollback() {
        return "触发熔断降级机制，或者说是传说中的：当前用户量过大，稍后再试";
    }
}
