package org.thenx.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;

import javax.annotation.Resource;

/**
 * @author thenx
 * <p>
 * Kafka 消费者监听
 */
@Slf4j
@Configuration
public class KafkaConsumer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 监听消息
     *
     * @param record 单条记录消费
     * @param acknowledgment 手工ack签收
     * @param consumer 消费者信息
     */
    @KafkaListener(groupId = "group02", topics = "topic02")
    public void getMessage(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        log.info("消费者监听到的消息： {}", record.value());
        // 手工签收机制
        acknowledgment.acknowledge();
    }
}
