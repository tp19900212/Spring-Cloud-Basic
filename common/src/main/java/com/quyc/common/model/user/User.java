package com.quyc.common.model.user;

import com.quyc.common.base.model.BaseEntitys;
import lombok.Data;

import java.util.Date;

/**
 * 用户表Entity
 *
 * @author quyc
 * @version 2019-07-30
 */
@Data
public class User extends BaseEntitys {

    private static final long serialVersionUID = 1L;
    private String name;        // 姓名

    private Integer age;        // 年龄

    private String email;        // 邮箱

    private String address;        // 地址

    private String password;        // 密码

    private String userId;        // 用户名

    private Date createDate;        // create_date

    private Date updateDate;        // update_date

    private String feature;        // feature

    private String phone;        // 手机号

}