package org.thenx.record.recordcloudserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author May
 * <p>
 * Eureka 服务端
 */
@EnableEurekaServer
@SpringBootApplication
public class RecordCloudServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudServerApplication.class, args);
    }

}
