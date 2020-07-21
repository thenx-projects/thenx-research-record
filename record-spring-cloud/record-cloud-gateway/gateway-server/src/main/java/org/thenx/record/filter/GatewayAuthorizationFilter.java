package org.thenx.record.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

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
}
