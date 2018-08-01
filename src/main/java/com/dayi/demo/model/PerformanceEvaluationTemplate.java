package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库PerformanceEvaluationTemplate表
 * 绩效考核表模板
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class PerformanceEvaluationTemplate {
    //UUID
    private String id;
    //起始时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //模板名称
    private String name;
    //模板对应的岗位id
    private String jobId;

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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }
}