package org.thenx.record.recordcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author May
 * <p>
 * 网关操作
 */
@EnableZuulProxy
@SpringBootApplication
public class RecordCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordCloudZuulApplication.class, args);
    }

}
