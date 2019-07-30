package com.quyc.servicetwo.user.service;

import com.alibaba.fastjson.JSONObject;
import com.quyc.common.model.user.User;
import com.quyc.servicetwo.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service(用户表)
 *
 * @author quyc
 * @version 2019-07-30
 */
@Service
@Slf4j
public class UserHelperService {

    @Autowired
    private UserDao userDao;

    /**
     * 获取分页总记录数
     *
     * @param map
     * @return
     */
    public int getTotalCount(JSONObject map) {
        int resCount = 0;
        Integer totalCount = userDao.getTotalCount(map);
        if (totalCount != null) {
            resCount = totalCount;
        }
        return resCount;
    }


    /**
     * save(保存用户表)
     *
     * @param user
     * @author quyc
     * @date 2018/1/30 14:59
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User save(User user) {
        User ret = null;
        if (user == null) {
            return ret;
        }
        if (user.getId() != null && user.getId() > 0) {
            updateById(user);
            ret = user;
        } else {
            user.setId(null);
            userDao.add(user);
            ret = user;
        }
        return ret;
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    /**
     * 获取单条数据
     *
     * @param user
     * @return
     * @author quyc
     */
    public User getSignleUser(User user) {
        return userDao.getSignleUser(user);
    }

    /**
     * getuserByNo(获取用户表单条数据--带缓存)
     *
     * @param userNo
     * @return
     * @author quyc
     */
    public User getUserByNo(String userNo) {
        if (StringUtils.isBlank(userNo)) {
            return null;
        }
        User user = new User();
        user.setUserId(userNo);
        return getSignleUser(user);
    }

    /**
     * 修改单条数据
     *
     * @param user
     * @return
     */
    public void updateById(User user) {
        userDao.updateById(user);
    }

}