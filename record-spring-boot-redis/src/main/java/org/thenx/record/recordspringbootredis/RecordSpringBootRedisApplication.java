package org.thenx.record.recordspringbootredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author May
 * <p>
 * Redis 操作
 */
@EnableEurekaClient
@SpringBootApplication
public class RecordSpringBootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordSpringBootRedisApplication.class, args);
    }

}
