package com.quyc.apione.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: andy
 * @create: 2019/6/3 19:24
 * @description: index service
 */
@FeignClient(value = "service-two")
public interface IndexTwoService {

    @RequestMapping("index")
    public String indexTwo(@RequestParam String name);
}
