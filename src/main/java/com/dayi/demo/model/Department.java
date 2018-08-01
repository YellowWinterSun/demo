package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库Department表
 * 部门表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class Department {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //部门名称
    private String name;
    //父级部门ID
    private String parentId;
    //部门总监工号
    private String majordomo;

    // getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getMajordomo() {
        return majordomo;
    }

    public void setMajordomo(String majordomo) {
        this.majordomo = majordomo == null ? null : majordomo.trim();
    }
}