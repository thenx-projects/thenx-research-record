package org.thenx.record.recordspringaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordspringaop.service.AopService;

/**
 * @author May
 * <p>
 * 对外暴露接口
 */
@RestController
public class AopController {

    /**
     * 返回一个 str
     *
     * @return str
     */
    @GetMapping(value = "/aop")
    public String aop() {
        AopService aopService = () -> "返回接口";
        return aopService.resp();
    }
}
