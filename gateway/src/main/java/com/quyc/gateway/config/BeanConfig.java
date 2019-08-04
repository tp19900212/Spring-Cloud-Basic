package com.quyc.gateway.config;

import com.quyc.gateway.filter.RequestTimeGatewayFilterFactory;
import com.quyc.gateway.filter.global.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: andy
 * @create: 2019/8/2 17:05
 * @description: bean config
 */
@Configuration
public class BeanConfig {

    /**
     * 注入自定义过滤器
     *
     * @return the request time gateway filter factory
     */
    @Bean
    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }

    /**
     * 注入自定义全局过滤器
     *
     * @return the token filter
     */
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    /**
     * 用于限流的键的解析器
     *
     * @return the host addr key resolver
     */
    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }

}
