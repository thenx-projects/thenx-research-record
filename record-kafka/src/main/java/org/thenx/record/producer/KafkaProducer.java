package org.thenx.record.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

/**
 * @author thenx
 * <p>
 * Kafka producer
 */
@Configuration
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String msg) {
        System.out.println("producer 消息： " + msg);
        kafkaTemplate.send("topic", msg);
    }
}
