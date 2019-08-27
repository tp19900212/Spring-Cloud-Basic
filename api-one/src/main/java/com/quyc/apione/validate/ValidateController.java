package com.quyc.apione.validate;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @progrem: learn
 * @description: Validate learn
 * @author: quyc
 * @create: 2019-08-27 21:47:33
 */
@RestController
@RequestMapping("validate")
public class ValidateController {

    /**
     * 手动获取校验信息并返回
     *
     * @param user
     * @param result
     * @return
     */
    @GetMapping("valid1")
    public String valid1(@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        }
        return "success";
    }

    /**
     * 使用全局异常处理返回校验信息
     *
     * @param user
     * @return
     */
    @GetMapping("valid2")
    public String valid2(@Validated User user) {
        return "success";
    }

    /**
     * 执行多个分组校验
     * 使用有序分组校验进行参数校验，实现多个检验分组的有序执行
     *
     * @param user
     * @return
     */
    @GetMapping("addUser")
    public String addUser(@Validated(GroupSequence.class) User user) {
        return "success";
    }

    /**
     * 执行单个分组校验
     *
     * @param user
     * @return
     */
    @GetMapping("updateUser")
    public String updateUser(@Validated({UpdateUser.class}) User user) {
        return "success";
    }

}

/**
 * 校验分组排序
 */
@javax.validation.GroupSequence({Default.class, AddUser.class, UpdateUser.class})
interface GroupSequence {
}