package com.quyc.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: andy
 * @create: 2019/8/1 20:25
 * @description: filter demo
 */
@Slf4j
public class RequestTimeFilter implements GatewayFilter, Ordered {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // pre handle
        long requestTimeBegin = System.currentTimeMillis();
        log.info("RequestTimeFilter request begin: {}", requestTimeBegin);
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, requestTimeBegin);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // post handle
            Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
            if (startTime != null) {
                log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
