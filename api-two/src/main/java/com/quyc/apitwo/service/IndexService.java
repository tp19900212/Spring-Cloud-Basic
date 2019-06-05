package com.quyc.apitwo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: andy
 * @create: 2019/6/3 19:24
 * @description: index service
 */
@FeignClient(value = "service-two")
public interface IndexService {

    @RequestMapping("index")
    public String index(@RequestParam String name);
}
