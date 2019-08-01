package com.quyc.gateway.config;

import com.quyc.gateway.filter.RequestTimeFilter;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: andy
 * @create: 2019/8/1 16:10
 * @description: simple route
 */
@Configuration
public class Routes {

    private static final String apiOne = "http://localhost:8182/";

    /**
     * 自定义路由器
     *
     * @param builder the builder
     * @return the route locator
     */
//    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        // localhost:8180/index/gateway -> localhost:8182:/index/gateway
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path("/index/gateway")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("hello", "world"))
                        .uri(apiOne))
                // 自定义fallback
                .route(predicateSpec -> predicateSpec
                        .path("/fallback")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .hystrix(config -> config
                                        .setName("fallback")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(apiOne))
                .build();

    }

    /**
     * 路由并重写路径，使用自定义过滤器
     *
     * @param builder the builder
     * @return the route locator
     */
    @Bean
    public RouteLocator customFilterRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        // 匹配路径
                        .path("/api-one/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                // 使用自定义过滤器
                                .filter(new RequestTimeFilter())
                                // 使用 RewritePathGatewayFilterFactory
                                .filter(new RewritePathGatewayFilterFactory().apply(config -> config
                                        .setRegexp("/api-one/(?<segment>.*)")
                                        .setReplacement("/$\\{segment}")))
                                // 添加返回信息
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        // 路由路径
                        .uri(apiOne)
                        // ？
                        .order(0)
                        .id("requestTimeRouter"))
                .build();
    }

}
