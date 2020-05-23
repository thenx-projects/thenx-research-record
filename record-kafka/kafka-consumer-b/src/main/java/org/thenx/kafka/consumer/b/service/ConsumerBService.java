package org.thenx.kafka.consumer.b.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author thenx
 *
 * Consumer B Service
 */
@Slf4j
@Configuration
public class ConsumerBService {

    public void getMsg(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        log.info("----> consumer b msg: {} & ack: {} & consumer: {}", record.value(), acknowledgment, consumer);
    }
}
