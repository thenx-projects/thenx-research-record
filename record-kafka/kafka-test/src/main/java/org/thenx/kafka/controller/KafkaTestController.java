package org.thenx.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.kafka.producer.service.KafkaProducer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author thenx
 * <p>
 * Kafka 对外暴露接口以便测试
 */
@Slf4j
@RestController
public class KafkaTestController {

    @Resource
    private KafkaProducer kafkaProducer;

    @GetMapping(value = "/")
    public Object testKafka(@RequestParam String msg) {
        String data = msg + UUID.randomUUID().toString();
        kafkaProducer.sendMsg("topic02", data);
        Map<String, String> map = new HashMap<>(4);
        map.put("topic02", data);
        return map;
    }
}
