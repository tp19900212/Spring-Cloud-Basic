package com.quyc.servicetwo.index.service;

import com.quyc.serviceone.spi.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: andy
 * @create: 2019/6/3 19:36
 * @description: index
 */
@RestController
@Slf4j
public class IndexServiceImpl implements com.quyc.servicetwo.spi.IndexService {

    @Resource
    private IndexService indexService;

    @Override
    public String index(String name) {
        log.info("index name={}", name);
        return indexService.index(name);
    }

}
