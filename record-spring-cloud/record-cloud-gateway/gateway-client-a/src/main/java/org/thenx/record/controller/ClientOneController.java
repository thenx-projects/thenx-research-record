package org.thenx.record.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 * <p>
 * 对外暴露客户端A
 */
@RestController
@RequestMapping(value = "/client")
public class ClientOneController {

    @GetMapping(value = "/a")
    public String getClientOne() {
        return "客户端 A !";
    }
}
