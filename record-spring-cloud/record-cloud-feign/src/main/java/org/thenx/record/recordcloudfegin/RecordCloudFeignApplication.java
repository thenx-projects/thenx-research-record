package org.thenx.record.recordcloudfegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author May
 *
 * Fegin 调用服务
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class RecordCloudFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudFeignApplication.class, args);
    }

}
