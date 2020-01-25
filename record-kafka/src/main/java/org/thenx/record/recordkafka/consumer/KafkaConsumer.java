package org.thenx.record.recordkafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author thenx
 * <p>
 * kafka 消费者
 */
@Component
public class KafkaConsumer {

    /**
     * 接收消息
     *
     * @param consumerRecord 消息
     * @param topic topic
     * @param consumer 异步：consumer.commitSync() 同步：consumer.commitAsync()
     */
    @KafkaListener(groupId = "groupId_1", topics = "topics_1")
    public void consumer(ConsumerRecord<String, Object> consumerRecord,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer) {
        System.out.println("----------> 消费者收到消息： " + consumerRecord.value() +
                "; topic: " + topic);
    }
}
