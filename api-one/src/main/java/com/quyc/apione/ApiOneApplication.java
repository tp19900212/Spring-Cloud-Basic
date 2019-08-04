package com.quyc.apione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.quyc.serviceone.spi","com.quyc.servicetwo.spi"})
@SpringBootApplication
@EnableHystrix
public class ApiOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiOneApplication.class, args);
    }

}
