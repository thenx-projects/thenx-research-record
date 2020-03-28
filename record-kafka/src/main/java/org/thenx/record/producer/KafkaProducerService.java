package org.thenx.record.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author thenx
 * <p>
 * $添加 Kafka 生产者
 */
@Configuration
public class KafkaProducerService {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Kafka 生产者
     *
     * @param topic  topic
     * @param object data
     */
    public void sendData(String topic, Object object) {
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, object);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("发送消息失败: {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                logger.info("发送消息成功： {}", stringObjectSendResult.toString());
            }
        });
    }
}
