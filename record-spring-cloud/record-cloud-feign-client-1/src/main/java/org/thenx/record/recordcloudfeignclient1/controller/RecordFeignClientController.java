package org.thenx.record.recordcloudfeignclient1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordcloudfeignclient1.service.EurekaClientService;
import org.thenx.record.recordcloudfeignclient1.service.RecordFeignClientService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * Feign 调用接口提供方后对外暴露接口
 */
@RestController
public class RecordFeignClientController {

    @Resource
    private RecordFeignClientService recordFeignClientService;

    @Resource
    private EurekaClientService eurekaClientService;

    /**
     * 调用 record-cloud-feign 对外开放的接口
     *
     * @return string
     */
    @GetMapping(value = "/client")
    public String client() {
        return recordFeignClientService.result();
    }

    /**
     * 调用 record-cloud-client-1 对外开放的接口
     *
     * @return string
     */
    @GetMapping(value = "/resp")
    public String resp() {
        return eurekaClientService.resp();
    }
}
