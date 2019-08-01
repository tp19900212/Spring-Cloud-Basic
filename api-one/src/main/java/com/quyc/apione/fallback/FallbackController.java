package com.quyc.apione.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: andy
 * @create: 2019/8/1 17:30
 * @description:
 */
@RestController
@RequestMapping("fallback")
public class FallbackController {

    @RequestMapping("")
    public String fallback(HttpServletRequest request) {
        return "this is fallback message";
    }

}
