package com.dayi.demo.controller.model;

import com.dayi.demo.model.OfficialEvaluationItem;

/**
 * 正表考核项目返回 类（用户页面展示的数据结构）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
public class OfficialItemRes extends OfficialEvaluationItem {
    //是否列表考核维度的首考核项目
    private boolean first;
    //该考核维度有几个考核项目
    private Integer itemNum;

    public OfficialItemRes(OfficialEvaluationItem source){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setOfficialId(source.getOfficialId());
        this.setDimensionalityName(source.getDimensionalityName());
        this.setDimensionalityLevel(source.getDimensionalityLevel());
        this.setName(source.getName());
        this.setWeight(source.getWeight());
        this.setContent(source.getContent());
        this.setSelfContent(source.getSelfContent());
        this.setSelfGrade(source.getSelfGrade());
        this.setBossGrade(source.getBossGrade());
    }

    public OfficialItemRes(OfficialEvaluationItem source, boolean first, Integer itemNum){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setOfficialId(source.getOfficialId());
        this.setDimensionalityName(source.getDimensionalityName());
        this.setDimensionalityLevel(source.getDimensionalityLevel());
        this.setName(source.getName());
        this.setWeight(source.getWeight());
        this.setContent(source.getContent());
        this.setSelfContent(source.getSelfContent());
        this.setSelfGrade(source.getSelfGrade());
        this.setBossGrade(source.getBossGrade());

        this.first = first;
        this.itemNum = itemNum;
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
}
