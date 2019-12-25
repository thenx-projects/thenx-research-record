package org.thenx.record.recordcloudfegin.service.impl;

import org.springframework.stereotype.Service;
import org.thenx.record.recordcloudfegin.service.RecordFeignService;

/**
 * @author May
 *
 * RecordFeignService 实现类
 */
@Service
public class RecordFeignServiceImpl implements RecordFeignService {

    /**
     * 结果集
     *
     * @return string
     */
    @Override
    public String result() {
        return "返回结果集";
    }
}
