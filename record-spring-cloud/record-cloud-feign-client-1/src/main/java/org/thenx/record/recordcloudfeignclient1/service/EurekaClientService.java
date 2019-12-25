package org.thenx.record.recordcloudfeignclient1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author May
 * <p>
 * 调用 record-cloud-client-1 的接口
 */
@FeignClient(name = "eureka-client-1", path = "eureka-client-1")
public interface EurekaClientService {

    /**
     * 定义接口方法
     *
     * @return string
     */
    @GetMapping("/ec")
    String resp();
}
