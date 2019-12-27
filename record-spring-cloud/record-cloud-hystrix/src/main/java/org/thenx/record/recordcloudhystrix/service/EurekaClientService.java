package org.thenx.record.recordcloudhystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author May
 * <p>
 * 通过 Feign调用 Eureka Client 1服务模块
 */
@FeignClient(name = "eureka-client-1", path = "eureka-client-1")
public interface EurekaClientService {

    /**
     * 调用 eureka-client-1 服务模块中的 ec 请求接口方法
     *
     * @return string
     */
    @GetMapping(value = "/ec")
    String ec();
}
