package com.dayi.demo.controller.model;

import com.dayi.demo.model.OfficialPerformanceEvaluation;

import java.util.Date;

/**
 * 绩效考核正表返回体（二次封装）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/29
 */
public class OfficialEvaluationRes extends OfficialPerformanceEvaluation {
    //薪资类别
    private String salaryType;
    //入职时间
    private Date entrydate;
    //主部门
    private String mainDepartment;

    public OfficialEvaluationRes(OfficialPerformanceEvaluation  source, String salaryType, Date entrydate){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setDepartmentName(source.getDepartmentName());
        this.setJobName(source.getJobName());
        this.setYear(source.getYear());
        this.setMonth(source.getMonth());
        this.setUserNo(source.getUserNo());
        this.setUserName(source.getUserName());
        this.setStatus(source.getStatus());
        this.setBoss1Remark(source.getBoss1Remark());
        this.setBoss2Remark(source.getBoss2Remark());
        this.setExGrade(source.getExGrade());
        this.setExReason(source.getExReason());
        this.setFinalGrade(source.getFinalGrade());
        this.setBoss1No(source.getBoss1No());
        this.setBoss1Name(source.getBoss1Name());
        this.setBoss2No(source.getBoss2No());
        this.setBoss2Name(source.getBoss2Name());
        this.setHrNo(source.getHrNo());
        this.setHrName(source.getHrName());

        this.salaryType = salaryType;
        this.entrydate = entrydate;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public String getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(String mainDepartment) {
        this.mainDepartment = mainDepartment;
    }
}
