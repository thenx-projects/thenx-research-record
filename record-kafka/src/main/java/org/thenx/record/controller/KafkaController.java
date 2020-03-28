package org.thenx.record.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.record.consumer.KafkaConsumer;
import org.thenx.record.producer.KafkaProducer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author thenx
 * <p>
 * Kafka 对外暴露接口
 */
@RestController
public class KafkaController {

    @Resource
    private KafkaProducer producer;

    @Resource
    private KafkaConsumer kafkaConsumer;

    /**
     * 接收发送的消息
     *
     * @param msg 消息内容
     * @return return msg
     */
    @GetMapping(value = "/send")
    public String sendMsg(@RequestParam(value = "msg", required = false) String msg) {
        if (msg == null || msg.isEmpty()) {
            producer.sendMsg("消息为空");
        } else {
            producer.sendMsg(msg);
        }
        return kafkaConsumer.receive(msg);
    }

    @GetMapping(value = "/sendList")
    public List<String> sendMsgList(@RequestParam(value = "msg", required = false) List<String> msg) {
        if (msg == null || msg.isEmpty()) {
            producer.sendMsg("消息为空");
        } else {
            producer.sendListMsg(msg);
        }
        return kafkaConsumer.receiveList(msg);
    }
}
