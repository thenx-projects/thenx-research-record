package org.thenx.record.recordspringaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author May
 *
 * AOP 相关操作
 */
@EnableEurekaClient
@SpringBootApplication
public class RecordSpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordSpringAopApplication.class, args);
    }

}
