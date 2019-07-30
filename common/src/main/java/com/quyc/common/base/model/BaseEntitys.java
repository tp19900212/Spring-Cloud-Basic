package com.quyc.common.base.model;

import java.io.Serializable;
import java.util.Date;


public class BaseEntitys implements Serializable {


    private static final long serialVersionUID = 1L;
    private String dbName = "mysql";    // 数据库名称
    private Long id;//数据主键Id
    private Date createTime = new Date();//创建时间
    private Date updateTime = new Date();//修改时间
    private Integer flag = 1;// 删除标记 1正常 -1删除
    private String remark;// 备注

    private String createtimestr;//创建时间
    private String updatetimestr;//修改时间

    public String getDbName() {
        return dbName;
    }


    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatetimestr() {
        return createtimestr;
    }


    public void setCreatetimestr(String createtimestr) {
        this.createtimestr = createtimestr;
    }


    public String getUpdatetimestr() {
        return updatetimestr;
    }


    public void setUpdatetimestr(String updatetimestr) {
        this.updatetimestr = updatetimestr;
    }


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}

