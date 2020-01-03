package org.thenx.record.recordspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author May
 * <p>
 * Security 权限控制
 */
@EnableEurekaClient
@SpringBootApplication
public class RecordSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordSpringSecurityApplication.class, args);
    }

}
