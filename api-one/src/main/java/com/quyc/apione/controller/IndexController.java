package com.quyc.apione.controller;

import com.quyc.serviceone.spi.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @create: 2019/6/3 19:22
 * @description: index
 */
@RestController
@RequestMapping("index")
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("")
    public String index(String name) {
        log.info("index name={}", name);
        return indexService.index(name);
    }

    @RequestMapping("gateway")
    public String gateway() {
        log.info("gateway");
        return "this message comes from api-one";
    }

}
