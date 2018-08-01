package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库Dimensionality表
 * 考核维度表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class Dimensionality {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //考核维度名称
    private String name;
    //考核维度优先级
    private Byte level;

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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}