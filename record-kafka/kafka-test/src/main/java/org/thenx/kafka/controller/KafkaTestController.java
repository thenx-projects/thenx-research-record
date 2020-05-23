package org.thenx.kafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.kafka.entity.KafkaTestEntity;
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

    @SneakyThrows
    @GetMapping(value = "/")
    public Object testKafka(@RequestParam String msg) {
        ObjectMapper mapper = new ObjectMapper();
        String data = msg + UUID.randomUUID().toString();
        KafkaTestEntity kafkaTestEntity = this.get();

        String s = mapper.writeValueAsString(kafkaTestEntity);
        kafkaProducer.sendMsg("topic02", s);
        Map<String, String> map = new HashMap<>(4);
        map.put("topic02", data);
        return map;
    }

    private KafkaTestEntity get() {
        KafkaTestEntity kafkaTestEntity = new KafkaTestEntity();
        kafkaTestEntity.setId(UUID.randomUUID().toString().replace("-", ""));
        kafkaTestEntity.setUserName("kafkaEntity Name");
        kafkaTestEntity.setPwd("1234");
        return kafkaTestEntity;
    }
}
