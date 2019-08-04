package com.quyc.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by quyuanchao on 2019/8/4 17:34.
 * <p>Title: </p>
 * <p>Description: 用于限流的键的解析器</p>
 */
public class HostAddrKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
