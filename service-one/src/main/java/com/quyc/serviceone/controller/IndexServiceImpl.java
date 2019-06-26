package com.quyc.serviceone.controller;

import com.quyc.serviceone.spi.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexService 实现
 * @author: andy
 * @create: 2019/6/3 19:36
 * @description: index
 */
@RestController
public class IndexServiceImpl implements IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Override
    public String index(String name) {
        logger.info("index name={}", name);
        return "hello " + name + ", this message comes from service-one";
    }

}
