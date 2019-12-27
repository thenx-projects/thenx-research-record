package org.thenx.record.recordcloudhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author May
 * <p>
 * Hystrix
 */
@EnableFeignClients
@SpringCloudApplication
public class RecordCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudHystrixApplication.class, args);
    }

}
