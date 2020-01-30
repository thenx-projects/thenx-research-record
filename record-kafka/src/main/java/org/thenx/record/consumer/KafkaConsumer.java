package org.thenx.record.consumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author thenx
 * <p>
 * Kafka consumer
 */
@Configuration
public class KafkaConsumer {

    @KafkaListener(topics = {"topic"})
    public String receive(String msg) {
        System.out.println("Kafka 接收消息： " + msg);
        return msg;
    }
}
