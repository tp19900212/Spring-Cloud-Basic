package com.quyc.servicetwo.spi;

import com.alibaba.fastjson.JSONObject;
import com.quyc.common.base.model.Partion;
import com.quyc.common.model.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Service(用户表)
 *
 * @author quyc
 * @version 2019-07-30
 */
@FeignClient(value = "service-two")
public interface UserService {

    /**
     * list:(查询用户表 带分页数据)
     *
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "user/getList", consumes = MediaType.APPLICATION_JSON_VALUE)
    Partion getList(@RequestBody JSONObject map);


    /**
     * getUserList:(查询用户表 不带分页数据)
     *
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "user/getUserList", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<User> getUserList(@RequestBody User user);


    /**
     * getUser:(查询用户表单个实体数据)
     *
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "user/getUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    User getUser(@RequestBody User user);

    /**
     * getuserByNo(获取用户表单条数据--带缓存)
     *
     * @param userNo
     * @param isCache
     * @return
     */
    @RequestMapping(value = "user/getUserByNo", consumes = MediaType.APPLICATION_JSON_VALUE)
    User getUserByNo(@RequestParam("userNo") String userNo, @RequestParam("isCache") boolean isCache);

    /**
     * save:(保存用户表数据)
     *
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "user/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    User save(@RequestBody User user);


    /**
     * delete:(删除用户表数据)
     *
     * @return
     * @Author quyc
     */
    @RequestMapping(value = "user/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean delete(@RequestParam("id") Long id);

}