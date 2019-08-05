package com.quyc.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @create: 2019/8/5 11:47
 * @description:
 */
@RestController
public class ConfigController {


    @Value("${name}")
    private String name;

    @RequestMapping("config")
    public String getConfig() {
        return name;
    }

}
