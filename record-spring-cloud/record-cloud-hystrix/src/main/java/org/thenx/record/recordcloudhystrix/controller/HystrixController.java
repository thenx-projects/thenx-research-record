package org.thenx.record.recordcloudhystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordcloudhystrix.service.EurekaClientService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * 熔断控制方法
 */
@RestController
public class HystrixController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EurekaClientService eurekaClientService;

    /**
     * 正常调用方法
     * <p>
     * 默认一秒，直接添加这个注解 HystrixCommand(fallbackMethod = "rollback")
     * <p>
     * 根据业务不同可做时间调整
     *
     * @return string
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000")
    }, fallbackMethod = "rollback")
    @GetMapping("/nm")
    public String normalMethod() {
        logger.info("\n -------> 正常调用Hystrix方法");
        return eurekaClientService.ec();
    }

    /**
     * 出发熔断机制回调 rollback 方法
     * <p>
     * 当上面的方法出现异常或在指定时间未返回时(默认超时时间1s),会调用此函数
     *
     * @return string
     */
    public String rollback() {
        logger.info("\n -------> 触发熔断机制回调 rollback 方法");
        return "触发熔断机制回调 rollback 方法";
    }
}
