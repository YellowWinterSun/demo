package com.dayi.demo.controller.model;

import com.dayi.demo.model.PerformanceEvaluationTemplate;

/**
 * 绩效考核模板表的返回类
 * （在绩效考核模板对象的基础上，增加了岗位名称和权重总分的显示）
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
public class TemplateRes extends PerformanceEvaluationTemplate {

    //部门名称
    private String departmentName;
    //岗位名称
    private String jobName;
    //权重总分
    private int weight;

    //考核人姓名
    private String userName;

    public TemplateRes(PerformanceEvaluationTemplate source, String jobName, int weight){
        this.setId(source.getId());
        this.setName(source.getName());
        this.setJobId(source.getJobId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.jobName = jobName;
        this.weight = weight;
    }

    public TemplateRes(PerformanceEvaluationTemplate source, String jobName, String departmentName){
        this.setId(source.getId());
        this.setName(source.getName());
        this.setJobId(source.getJobId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.jobName = jobName;
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
