package com.quyc.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

/**
 * @author: andy
 * @create: 2019/8/2 16:33
 * @description: request time gateway filter factory
 */
@Slf4j
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // pre handle
            long requestTimeBegin = System.currentTimeMillis();
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, requestTimeBegin);
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        // post handle
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            String msg = exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms";
                            if (config.withParam) {
                                msg = msg + " params:" + exchange.getRequest().getQueryParams();
                            }
                            log.info(msg);
                        }
                    }));
        };
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * yml中的filtername
     *
     * @return
     */
    @Override
    public String name() {
        return "RequestTime";
    }

    @Data
    public static class Config {

        private boolean withParam;
    }
}
