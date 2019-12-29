package org.thenx.record.recordspringaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param param 整型参数
     * @return str
     */
    @GetMapping(value = "/aop")
    public String aop(@RequestParam("param") Integer param) {
        AopService aopService = (p) -> "返回接口";
        if (param == 1) {
            return aopService.resp(param);
        } else {
            throw new RuntimeException("传入参数不为 1");
        }
    }
}
