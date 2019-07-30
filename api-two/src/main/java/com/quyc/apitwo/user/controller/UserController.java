package com.quyc.apitwo.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quyc.common.base.model.DataReturn;
import com.quyc.common.base.model.Partion;
import com.quyc.common.base.utils.StringUtils;
import com.quyc.common.model.user.User;
import com.quyc.servicetwo.spi.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * UserController(用户表)
 * Controller 层的异常应该统一捕获进行处理，这样业务代码更加清晰
 *
 * @author quyc
 * @version 2019-07-30
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * getList:(获取用户表分页查询接口)
     *
     * @param request
     * @param parms
     * @return
     * @Author quyc
     */
    @RequestMapping("getList")
    public DataReturn getList(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        log.info("getList:(获取用户表分页查询接口) 开始  parms={}", parms);
        if (StringUtils.isBlank(parms)) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        JSONObject json = JSONObject.parseObject(parms);
        if (json == null) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        int pageNo = json.getIntValue("pageNo");
        int pageSize = json.getIntValue("pageSize");
        JSONObject param = StringUtils.getPageJSONObject(pageNo, pageSize);
        param.putAll(json);
        Partion pt = userService.getList(param);
        List<User> list = null;
        int totalCount = 0;
        if (pt != null) {
            list = (List<User>) pt.getList();
            totalCount = pt.getPageCount();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);
        jsonObject.put("totalCount", totalCount);
        dataReturn.setData(jsonObject);
        dataReturn.setCode(DataReturn.SUCCESS);
        log.info("getList:(获取用户表分页查询接口) 结束  parms={}", parms);
        return dataReturn;
    }

    /**
     * getUserDetail:(查询用户表详情数据接口)
     *
     * @param request
     * @param parms
     * @return
     * @Author quyc
     */
    @RequestMapping("getUserDetail")
    public DataReturn getUserDetail(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        log.info("getUser:(查询用户表详情数据接口) 开始  parms={}", parms);
        if (StringUtils.isBlank(parms)) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        JSONObject json = JSONObject.parseObject(parms);
        if (json == null) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        User user = JSON.toJavaObject(json, User.class);
        if (user == null) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        User retuser = userService.getUser(user);
        dataReturn.setData(retuser);
        dataReturn.setCode(DataReturn.SUCCESS);
        log.info("getUser:(查询用户表详情数据接口) 结束");
        return dataReturn;
    }

    /**
     * save:(保存用户表数据接口)
     *
     * @param request
     * @param parms
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "save")
    public DataReturn save(HttpServletRequest request, String parms) {
        DataReturn dataReturn = new DataReturn();
        log.info("save:(保存用户表数据接口) 开始  parms={}", parms);
        if (StringUtils.isBlank(parms)) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        JSONObject json = JSONObject.parseObject(parms);
        if (json == null) {
            dataReturn.setMessage("参数为空");
            return dataReturn;
        }
        User user = JSON.toJavaObject(json, User.class);
        // 无保存内容
        if (user == null) {
            dataReturn.setMessage("无保存内容");
            return dataReturn;
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // 保存数据库
        User ret = userService.save(user);
        if (ret != null) {
            dataReturn.setCode(DataReturn.SUCCESS);
            dataReturn.setMessage("保存成功");
        }
        log.info("save:(保存用户表数据接口) 结束");
        return dataReturn;
    }

}