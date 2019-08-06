package com.quyc.apione.localconfig;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @create: 2019/8/6 17:17
 * @description:
 */
@RestController
@RequestMapping("localconfig")
@EnableConfigurationProperties
public class LocalConfigController {

    @Autowired
    ConfigBean configBean;

    @RequestMapping("config")
    public String config() {
        return JSON.toJSONString(configBean);
    }

}
