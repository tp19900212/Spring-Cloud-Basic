package com.quyc.apitwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.quyc.servicetwo.spi"})
public class ApiTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTwoApplication.class, args);
    }

}
