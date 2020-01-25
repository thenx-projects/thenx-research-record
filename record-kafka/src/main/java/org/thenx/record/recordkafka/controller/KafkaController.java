package org.thenx.record.recordkafka.controller;

import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.recordkafka.producer.KafkaProducer;

import javax.annotation.Resource;

/**
 * @author thenx
 * <p>
 * Kafka 功能模块 对外接口
 */
@RestController
public class KafkaController {

    @Resource
    private KafkaProducer kafkaProducer;

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * Kafka 对外接口测试
     */
    @GetMapping(value = "/test")
    public void testKafka() {
        kafkaProducer.sendMsg("topics_1", "FRAK");
    }
}
