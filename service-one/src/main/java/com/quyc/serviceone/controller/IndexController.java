package com.quyc.serviceone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: andy
 * @create: 2019/6/3 19:36
 * @description: index
 */
@RestController
@RequestMapping("index")
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("")
    public String index(String name, HttpServletRequest httpServletRequest) {
        logger.info("index name={}", name);
        return "hello " + name + ", this message comes from service-one";
    }

}
