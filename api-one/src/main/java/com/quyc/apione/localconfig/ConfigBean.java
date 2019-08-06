package com.quyc.apione.localconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @create: 2019/8/6 17:12
 * @description: load local config
 */
@Data
@Component
@ConfigurationProperties(prefix = "localconfig")
public class ConfigBean {

    private String name;
    private int age;
    private int number;
    private String uuid;
    private int max;
    private String value;
    private String greeting;

}
