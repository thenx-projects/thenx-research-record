package org.thenx.kafka.entity;

import lombok.Data;

/**
 * @author thenx
 * <p>
 * Kafka 实体接受与发送测试
 */
@Data
public class KafkaTestEntity {

    /**
     * id
     */
    private String id;

    /**
     * user name
     */
    private String userName;

    /**
     * password
     */
    private String pwd;
}
