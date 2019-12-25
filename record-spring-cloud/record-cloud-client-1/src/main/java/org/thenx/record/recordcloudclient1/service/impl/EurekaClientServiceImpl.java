package org.thenx.record.recordcloudclient1.service.impl;

import org.springframework.stereotype.Service;
import org.thenx.record.recordcloudclient1.service.EurekaClientService;

/**
 * @author May
 * <p>
 * 实现对应的接口
 */
@Service
public class EurekaClientServiceImpl implements EurekaClientService {

    /**
     * 添加返回值接口
     *
     * @return string
     */
    @Override
    public String resp() {
        return "调用到了Eureka-Client-1";
    }
}
