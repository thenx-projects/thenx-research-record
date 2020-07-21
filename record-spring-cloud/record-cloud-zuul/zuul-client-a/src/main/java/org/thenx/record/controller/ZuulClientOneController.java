package org.thenx.record.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 * <p>
 * Zuul 客户端第一个
 */
@RestController
@RequestMapping(value = "/client")
public class ZuulClientOneController {

    @GetMapping(value = "/a")
    public String getZuulClientA() {
        return "客户端A !";
    }
}
