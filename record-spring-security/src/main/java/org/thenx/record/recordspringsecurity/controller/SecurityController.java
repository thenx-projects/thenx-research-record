package org.thenx.record.recordspringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 * <p>
 * 测试请求Security方法
 */
@RestController
public class SecurityController {

    @GetMapping(value = "/info")
    public String get() {
        return "请求到了安全方法";
    }
}
