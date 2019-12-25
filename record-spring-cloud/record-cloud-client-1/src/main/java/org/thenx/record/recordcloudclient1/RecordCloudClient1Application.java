package org.thenx.record.recordcloudclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author May
 * <p>
 * Eureka 客户端 1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RecordCloudClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudClient1Application.class, args);
    }

}
