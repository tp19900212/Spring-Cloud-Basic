package com.quyc.servicetwo.user.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quyc.common.base.model.Partion;
import com.quyc.common.model.user.User;
import com.quyc.servicetwo.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Service(用户表)
 * service 层的异常应该统一捕获进行处理，这样业务代码更加清晰
 *
 * @author quyc
 * @version 2019-07-30
 */
@RestController
@RequestMapping(value = "/user/")
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserHelperService userHelperService;
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * getList(获取用户表带分页数据-服务)
     *
     * @param json
     * @return
     * @author quyc
     */
    @RequestMapping("getList")
    public Partion getList(@RequestBody JSONObject json) {
        logger.info("getList(获取用户表带分页数据-服务) 开始 json={}", json);
        if (json == null || json.size() < 1) {
            return null;
        }
        int totalcount = userHelperService.getTotalCount(json);
        List<User> list = null;
        if (totalcount > 0) {
            list = userDao.getList(json);
        }
        Partion pt = new Partion(json, list, totalcount);
        logger.info("getList(获取用户表带分页数据-服务) 结束 ");
        return pt;
    }

    /**
     * getUserList(获取用户表 不带分页数据-服务)
     *
     * @param user
     * @return
     * @author quyc
     */
    @RequestMapping("getUserList")
    public List<User> getUserList(@RequestBody User user) {
        String parms = JSON.toJSONString(user);
        List<User> list = null;
        logger.info("getUserList(获取用户表 不带分页数据-服务) 开始 parms={}", parms);
        if (user == null) {
            return list;
        }
        list = userDao.getUserList(user);
        logger.info("getUserList(获取用户表 不带分页数据-服务) 结束");
        return list;
    }


    /**
     * save (保存用户表 数据-服务)
     *
     * @param user
     * @return
     * @author quyc
     */
    @RequestMapping("save")
    public User save(@RequestBody User user) throws Exception {
        String parms = JSON.toJSONString(user);
        logger.info("save (保存用户表 数据-服务) 开始 parms={}", parms);
        User ret = null;
        if (user == null) {
            return ret;
        }
        ret = userHelperService.save(user);
        logger.info("save (保存用户表 数据-服务) 结束");
        return ret;
    }


    /**
     * getUser(获取用户表单条数据-服务)
     *
     * @param user
     * @return
     * @author quyc
     */
    @RequestMapping("getUser")
    public User getUser(@RequestBody User user) {
        User ret = null;
        String parms = JSON.toJSONString(user);
        List<User> list = null;
        logger.info("getUser(获取用户表单条数据-服务) 开始 parms={}", parms);
        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (user == null) {
            return ret;
        }
        ret = userHelperService.getSignleUser(user);
        logger.info("getUser(获取用户表单条数据-服务) 结束 ");
        return ret;
    }


    /**
     * getuserByNo(获取用户表单条数据--带缓存)
     *
     * @param userNo
     * @param isCache
     * @return
     * @author quyc
     */
    @RequestMapping("getUserByNo")
    public User getUserByNo(String userNo, boolean isCache) {
        User ret = null;
        logger.info("getuserByNo(获取用户表单条数据--带缓存) 开始 userNo={},isCache={}", userNo, isCache);
        if (userNo == null) {
            return ret;
        }
        ret = userHelperService.getUserByNo(userNo);
        logger.info("getuserByNo(获取用户表单条数据--带缓存) 单条数据-服务) 结束 " + JSON.toJSONString(ret));
        return ret;
    }

    /**
     * delete(逻辑删除用户表数据-服务)
     *
     * @param id
     * @return
     * @author quyc
     */
    @RequestMapping("delete")
    public boolean delete(Long id) {
        logger.info("delete(逻辑删除用户表数据-服务) 开始 id={}", id);
        boolean isSuccess = false;
        if (id < 1) {
            return isSuccess;
        }
        User dt = userHelperService.getUserById(id);
        if (dt == null) {
            return isSuccess;
        }
        userDao.delete(id);
        isSuccess = true;
        logger.info("delete(逻辑删除用户表数据-服务)结束 id={},isSuccess={}", id, isSuccess);
        return isSuccess;
    }
}