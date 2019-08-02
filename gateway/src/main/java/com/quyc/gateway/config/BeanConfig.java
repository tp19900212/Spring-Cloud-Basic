package com.quyc.gateway.config;

import com.quyc.gateway.filter.RequestTimeGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: andy
 * @create: 2019/8/2 17:05
 * @description: bean config
 */
@Configuration
public class BeanConfig {

    @Bean
    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }

}
