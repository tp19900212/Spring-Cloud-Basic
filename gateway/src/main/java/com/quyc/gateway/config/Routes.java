package com.quyc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * @author: andy
 * @create: 2019/8/1 16:10
 * @description: simple route
 */
@Configuration
public class Routes {

    /**
     * 自定义路由器
     *
     * @param builder the builder
     * @return the route locator
     */
//    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String apiOne = "http://localhost:8182/";
        // localhost:8180/index/gateway -> localhost:8182:/index/gateway
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/index/gateway")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.addRequestHeader("hello", "world"))
                        .uri(apiOne))
                // 自定义fallback
                .route(predicateSpec -> predicateSpec.path("/fallback")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .hystrix(config -> config
                                        .setName("fallback")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(apiOne))
                .build();

    }

}
