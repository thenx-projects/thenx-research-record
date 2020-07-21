package org.thenx.record;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author May
 * <p>
 * Zuul 第一个客户端
 */
@SpringCloudApplication
public class ZuulClientOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulClientOneApplication.class, args);
    }
}
