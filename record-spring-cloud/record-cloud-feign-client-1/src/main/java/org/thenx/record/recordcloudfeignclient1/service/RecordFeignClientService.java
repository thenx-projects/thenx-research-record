package org.thenx.record.recordcloudfeignclient1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author May
 * <p>
 * 调用 Feign服务提供模块
 */
@FeignClient(name = "feign-server", path = "/feign")
public interface RecordFeignClientService {

    /**
     * Feign拿到
     *
     * @return string
     */
    @GetMapping(value = "/result")
    String result();
}
