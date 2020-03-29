package org.thenx.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author thenx
 * <p>
 * Kafka Producer
 */
@Slf4j
@Configuration
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMsg(String topic, Object object) {
        log.info("消息开始发送： {}", topic);
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, object);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("消息发送失败： {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info("消息发送成功： {}", stringObjectSendResult.toString());
            }
        });
    }
}
