package org.thenx.record.recordspringbootswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author thenx
 *
 * 启动类添加开启Swagger注解
 */
@EnableSwagger2
@SpringBootApplication
public class RecordSpringBootSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordSpringBootSwaggerApplication.class, args);
    }

}
