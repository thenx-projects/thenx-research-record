package org.thenx.record.recordspringbootswagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author May
 * <p>
 * Swagger 集成接口文档
 */
@Configuration
@EnableSwagger2
public class SwaggerApplication {

    /**
     * 创建 swagger api
     *
     * @return api
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.thenx.record"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api 信息
     *
     * @return msg
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("thenx 开源项目接口文档")
                .description("powered by thenx")
                .termsOfServiceUrl("https://www.thenx.org")
                .version("1.0")
                .build();
    }
}