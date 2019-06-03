package com.quyc.apione.controller;

import com.quyc.apione.service.IndexService;
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
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("")
    public String index(String name) {
        return indexService.index(name);
    }

}
