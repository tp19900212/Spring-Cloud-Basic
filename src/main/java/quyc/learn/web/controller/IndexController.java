package quyc.learn.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andy
 * @create: 2019/5/24 17:58
 * @description: index
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "welcome to my springboot progrem";
    }

}
