package com.quyc.apitwo.index;

import com.alibaba.fastjson.JSONObject;
import com.quyc.apitwo.user.UserController;
import com.quyc.servicetwo.spi.IndexService;
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

    @Resource
    private IndexService indexService;
    @Autowired
    private UserController userController;

    @RequestMapping("")
    public String index(String name) throws InterruptedException {
        log.info("index name={}", name);
        return indexService.index(name);
    }

    @RequestMapping("sentinel")
    // 指定该接口为sentinel监控接口
//    @SentinelResource("sentinelApi")
    public String sentinel(String name) {
        return "hello " + name;
    }

    @RequestMapping("testHystrixCommand")
    public void test() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            JSONObject object = new JSONObject();
            object.put("name", i);
            new Thread(() -> userController.getList(object.toJSONString())).start();
        }
        Thread.sleep(400);
        for (int i = 0; i < 3; i++) {
            JSONObject object = new JSONObject();
            object.put("name", i);
            new Thread(() -> userController.getUserDetail(object.toJSONString())).start();
        }
//        Thread.sleep(400);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                try {
//                    index(String.valueOf(finalI));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }

    }

}
