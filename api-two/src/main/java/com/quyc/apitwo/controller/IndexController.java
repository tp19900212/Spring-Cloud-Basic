package com.quyc.apitwo.controller;

import com.quyc.servicetwo.spi.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: andy
 * @create: 2019/6/3 19:22
 * @description: index
 */
@RestController
@RequestMapping("index")
@Slf4j
public class IndexController {

    @Resource
    private IndexService indexService;

    @RequestMapping("")
    public String index(String name) {
        log.info("index name={}", name);
        return indexService.index(name);
    }

    @RequestMapping("sentinel")
    // 指定该接口为sentinel监控接口
//    @SentinelResource("sentinelApi")
    public String sentinel(String name) {
        return "hello " + name;
    }

}
