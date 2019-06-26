package com.quyc.serviceone.spi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author quyuanchao
 * @date 2019/6/25 22:11
 * <p>Title: </p>
 * <p>Description: </p>
 */
@FeignClient(value = "service-one")
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
