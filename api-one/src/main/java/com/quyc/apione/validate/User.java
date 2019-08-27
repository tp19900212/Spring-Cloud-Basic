package com.quyc.apione.validate;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @progrem: learn
 * @description: validated bean
 * @author: quyc
 * @create: 2019-08-27 23:05:19
 */
@Data
public class User {

    /**
     * 获取 ValidationMessages.properties 中配置的校验信息
     */
    @NotEmpty(message = "{edit.username.null}", groups = Default.class)
    private String userName;

    @NotEmpty(message = "password is null", groups = Default.class)
    @Size(min = 6, max = 20, message = "{edit.password.size}", groups = AddUser.class)
    private String password;

    @Min(value = 0, message = "{edit.age.min}", groups = Default.class)
    private Integer age;

    @NotNull(groups = UpdateUser.class, message = "updateTime is null")
    private String updateTime;

    @NotNull(groups = AddUser.class, message = "createTime is null")
    private String createTime;
}
