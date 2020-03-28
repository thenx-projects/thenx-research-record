package org.thenx.record.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author thenx
 * <p>
 * Kafka producer
 */
@Configuration
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, List<String>> listKafkaTemplate;

    /**
     * string 类型生产者
     *
     * @param msg msg消息
     */
    public void sendMsg(String msg) {
        System.out.println("producer 消息： " + msg);
        kafkaTemplate.send("topic", msg);
    }

    /**
     * list 类型生产者
     *
     * @param list list
     */
    public void sendListMsg(List<String> list) {
        System.out.println("producer list 消息： " + list);
        listKafkaTemplate.send("listTopic", list);
    }

}
