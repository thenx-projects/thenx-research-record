package org.thenx.research.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 * <p>
 * 添加测试接口
 */
@RestController
@RequestMapping(value = "/")
public class ClientController {

    /**
     * 客户端1 接口
     *
     * @return string
     */
    @GetMapping(value = "/")
    public String  client1() {
        return "Actuator 客户端 1";
    }

}
