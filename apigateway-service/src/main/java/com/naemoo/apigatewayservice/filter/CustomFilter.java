package com.naemoo.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ChannelSendOperator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory {


    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter : request ID - > {}", request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(
                    () ->{
                        log.info("Custom POST Filter : response filter : {}", response.getStatusCode());
                    }
            ));

        });
    }
}
