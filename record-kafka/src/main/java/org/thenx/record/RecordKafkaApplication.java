package org.thenx.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author thenx
 * <p>
 * Kafka 启动功能模块
 */
@SpringBootApplication
public class RecordKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordKafkaApplication.class, args);
    }

}
