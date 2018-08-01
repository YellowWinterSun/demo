package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库EvaluationItem表
 * 模板-考核项目表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class EvaluationItem {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //考核项目名称
    private String name;
    //权重（单位百分比）
    private Byte weight;
    //定义及考核标准
    private String content;
    //所属考核维度ID
    private String dimensionalityId;

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

    public Byte getWeight() {
        return weight;
    }

    public void setWeight(Byte weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDimensionalityId() {
        return dimensionalityId;
    }

    public void setDimensionalityId(String dimensionalityId) {
        this.dimensionalityId = dimensionalityId == null ? null : dimensionalityId.trim();
    }
}