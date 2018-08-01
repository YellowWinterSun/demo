package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库OfficialEvaluationItem表
 * 绩效考核表-考核项目
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class OfficialEvaluationItem {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //所属的绩效考核表ID
    private String officialId;
    //所属考核维度名称
    private String dimensionalityName;
    //所属考核维度优先级
    private Byte dimensionalityLevel;
    //考核项目名
    private String name;
    //权重
    private Byte weight;
    //定义及评分标准
    private String content;
    //考核人自评理由
    private String selfContent;
    //考核人自评分
    private Byte selfGrade;
    //直属上司评分
    private Byte bossGrade;

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

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId == null ? null : officialId.trim();
    }

    public String getDimensionalityName() {
        return dimensionalityName;
    }

    public void setDimensionalityName(String dimensionalityName) {
        this.dimensionalityName = dimensionalityName == null ? null : dimensionalityName.trim();
    }

    public Byte getDimensionalityLevel() {
        return dimensionalityLevel;
    }

    public void setDimensionalityLevel(Byte dimensionalityLevel) {
        this.dimensionalityLevel = dimensionalityLevel;
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

    public String getSelfContent() {
        return selfContent;
    }

    public void setSelfContent(String selfContent) {
        this.selfContent = selfContent == null ? null : selfContent.trim();
    }

    public Byte getSelfGrade() {
        return selfGrade;
    }

    public void setSelfGrade(Byte selfGrade) {
        this.selfGrade = selfGrade;
    }

    public Byte getBossGrade() {
        return bossGrade;
    }

    public void setBossGrade(Byte bossGrade) {
        this.bossGrade = bossGrade;
    }
}