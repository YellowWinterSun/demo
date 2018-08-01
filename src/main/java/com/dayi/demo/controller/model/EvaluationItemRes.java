package com.dayi.demo.controller.model;

import com.dayi.demo.model.EvaluationItem;

/**
 * 模板 - 考核项目返回体对象
 * 用于二次封装考核项目对象，返回前台做数据展示用
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
public class EvaluationItemRes extends EvaluationItem {
    //考核维度名
    private String dimensionalityName;
    //考核维度优先级
    private int level;

    public EvaluationItemRes(EvaluationItem source, String dimensionalityName, int level){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setName(source.getName());
        this.setWeight(source.getWeight());
        this.setContent(source.getContent());
        this.setDimensionalityId(source.getDimensionalityId());
        this.dimensionalityName = dimensionalityName;
        this.level = level;
    }

    public String getDimensionalityName() {
        return dimensionalityName;
    }

    public void setDimensionalityName(String dimensionalityName) {
        this.dimensionalityName = dimensionalityName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
