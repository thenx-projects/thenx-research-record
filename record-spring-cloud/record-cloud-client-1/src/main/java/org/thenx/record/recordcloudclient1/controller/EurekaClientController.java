package org.thenx.record.recordcloudclient1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordcloudclient1.service.EurekaClientService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * 对外暴露接口
 */
@RestController
public class EurekaClientController {

    @Resource
    private EurekaClientService eurekaClientService;

    /**
     * 暴露接口方法
     *
     * @return string
     */
    @GetMapping(value = "/ec")
    public String resp() {
        return eurekaClientService.resp();
    }
}
