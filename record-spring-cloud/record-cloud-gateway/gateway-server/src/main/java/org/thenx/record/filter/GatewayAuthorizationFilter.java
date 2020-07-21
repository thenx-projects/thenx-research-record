package org.thenx.record.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author May
 * <p>
 * Gateway 全局鉴权
 */
@Configuration
public class GatewayAuthorizationFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpRequest.Builder mutate = request.mutate();
            ServerHttpResponse response = exchange.getResponse();

            ServerHttpRequest build = mutate.build();
            return chain.filter(exchange.mutate().request(build).build());
        };
    }

    /**
     * 自定义返回错误信息
     *
     * @param response {@link org.springframework.http.server.ServletServerHttpResponse}
     * @param status   {@link String}
     * @param message  {@link String}
     * @return {@link DataBuffer}
     */
    public DataBuffer responseErrorInfo(ServerHttpResponse response, String status, String message) {
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        Map<String, String> map = new HashMap<>(4);
        map.put("status", status);
        map.put("message", message);
        return response.bufferFactory().wrap(map.toString().getBytes());
    }
}
