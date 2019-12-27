package org.thenx.record.recordcloudhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author May
 * <p>
 * Hystrix
 */
@SpringCloudApplication
public class RecordCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudHystrixApplication.class, args);
    }

}
