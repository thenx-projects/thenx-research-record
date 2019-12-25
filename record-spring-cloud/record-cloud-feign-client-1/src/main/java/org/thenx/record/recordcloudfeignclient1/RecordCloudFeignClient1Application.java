package org.thenx.record.recordcloudfeignclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author May
 *
 * Feign 调用接口
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class RecordCloudFeignClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudFeignClient1Application.class, args);
    }

}
