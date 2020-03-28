package org.thenx.record.consumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

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

    @KafkaListener(topics = {"listTopic"})
    public List<String> receiveList(List<String> list) {
        System.out.println("Kafka 接收 list 消息： " + list);
        return list;
    }
}
