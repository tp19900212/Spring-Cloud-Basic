package com.quyc.apione.index;

import com.quyc.serviceone.spi.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IndexService indexService;
    @Resource
    private com.quyc.servicetwo.spi.IndexService indexTwoService;
    @RequestMapping("")
    public String index(String name) {
        log.info("index name={}", name);
        return indexService.index(name);
    }

    @RequestMapping("two")
    public String two(String name) throws InterruptedException {
        log.info("two name={}",name);
        return indexTwoService.index(name);
    }

}
