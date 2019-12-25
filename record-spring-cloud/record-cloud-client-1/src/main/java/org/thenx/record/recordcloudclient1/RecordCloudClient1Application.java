package org.thenx.record.recordcloudclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author May
 * <p>
 * Eureka 客户端 1
 */
@EnableEurekaClient
@SpringBootApplication
public class RecordCloudClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudClient1Application.class, args);
    }

}
