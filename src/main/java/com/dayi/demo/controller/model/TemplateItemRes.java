package com.dayi.demo.controller.model;

/**
 * 考核模板项目类（用于页面展示的数据结构)
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/26
 */
public class TemplateItemRes {
    //考核维度
    private String dimensionalityId;
    //考核维度名
    private String dimensionalityName;
    //考核项目id
    private String id;
    //考核项目名
    private String name;
    //权重
    private Integer weight;
    //评分标准
    private String content;
    //是否列表考核维度的首考核项目
    private boolean first;
    //该考核维度有多少个考核项目
    private Integer itemNum;
    //自评内容
    private String selfContent;
    //自评分
    private Integer selfGrade;
    //直接上级评分
    private Integer bossGrade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDimensionalityId() {
        return dimensionalityId;
    }

    public void setDimensionalityId(String dimensionalityId) {
        this.dimensionalityId = dimensionalityId;
    }

    public String getDimensionalityName() {
        return dimensionalityName;
    }

    public void setDimensionalityName(String dimensionalityName) {
        this.dimensionalityName = dimensionalityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public String getSelfContent() {
        return selfContent;
    }

    public void setSelfContent(String selfContent) {
        this.selfContent = selfContent;
    }

    public Integer getSelfGrade() {
        return selfGrade;
    }

    public void setSelfGrade(Integer selfGrade) {
        this.selfGrade = selfGrade;
    }

    public Integer getBossGrade() {
        return bossGrade;
    }

    public void setBossGrade(Integer bossGrade) {
        this.bossGrade = bossGrade;
    }
}
