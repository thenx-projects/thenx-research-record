package org.thenx.record.recordcloudfeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordcloudfeign.service.RecordFeignService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * 对外暴露接口
 */
@RestController
public class RecordFeignController {

    @Resource
    private RecordFeignService recordFeignService;

    /**
     * 常规调用 Service
     *
     * @return string
     */
    @GetMapping(value = "/result")
    public String result() {
        return recordFeignService.result();
    }
}
