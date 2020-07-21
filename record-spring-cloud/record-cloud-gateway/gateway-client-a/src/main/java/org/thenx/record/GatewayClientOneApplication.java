package org.thenx.record;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author May
 * <p>
 * Gateway client a
 */
@SpringCloudApplication
public class GatewayClientOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayClientOneApplication.class, args);
    }
}
