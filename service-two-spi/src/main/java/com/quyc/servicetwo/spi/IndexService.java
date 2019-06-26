package com.quyc.servicetwo.spi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IndexService服务接口
 *
 * @author: andy
 * @create: 2019/6/3 19:24
 * @description: index service
 */
@FeignClient(value = "service-two")
public interface IndexService {

    /**
     * index接口
     *
     * @param name
     * @return
     */
    @RequestMapping("index/")
    String index(@RequestParam("name") String name);
}
