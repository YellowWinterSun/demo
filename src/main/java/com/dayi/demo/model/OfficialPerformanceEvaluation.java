package com.dayi.demo.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据库OfficialPerformanceEvaluation表
 * 绩效考核表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class OfficialPerformanceEvaluation {
    //UUID
    private String id;
    //起始时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //部门名称
    private String departmentName;
    //岗位名称
    private String jobName;
    //考核年
    private Short year;
    //考核月
    private Byte month;
    //考核人工号
    private String userNo;
    //考核人姓名
    private String userName;
    //考核状态
    private String status;
    //直属上司评语
    private String boss1Remark;
    //部门负责人评语
    private String boss2Remark;
    //特别分
    private Byte exGrade;
    //特别分的理由
    private String exReason;
    //最终得分
    private BigDecimal finalGrade;
    //直属上司工号
    private String boss1No;
    //直属上司姓名
    private String boss1Name;
    //部门负责人工号
    private String boss2No;
    //部门负责人姓名
    private String boss2Name;
    //人力行政负责人工号
    private String hrNo;
    //人力行政负责人姓名
    private String hrName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBoss1Remark() {
        return boss1Remark;
    }

    public void setBoss1Remark(String boss1Remark) {
        this.boss1Remark = boss1Remark == null ? null : boss1Remark.trim();
    }

    public String getBoss2Remark() {
        return boss2Remark;
    }

    public void setBoss2Remark(String boss2Remark) {
        this.boss2Remark = boss2Remark == null ? null : boss2Remark.trim();
    }

    public Byte getExGrade() {
        return exGrade;
    }

    public void setExGrade(Byte exGrade) {
        this.exGrade = exGrade;
    }

    public String getExReason() {
        return exReason;
    }

    public void setExReason(String exReason) {
        this.exReason = exReason == null ? null : exReason.trim();
    }

    public BigDecimal getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(BigDecimal finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getBoss1No() {
        return boss1No;
    }

    public void setBoss1No(String boss1No) {
        this.boss1No = boss1No == null ? null : boss1No.trim();
    }

    public String getBoss1Name() {
        return boss1Name;
    }

    public void setBoss1Name(String boss1Name) {
        this.boss1Name = boss1Name == null ? null : boss1Name.trim();
    }

    public String getBoss2No() {
        return boss2No;
    }

    public void setBoss2No(String boss2No) {
        this.boss2No = boss2No == null ? null : boss2No.trim();
    }

    public String getBoss2Name() {
        return boss2Name;
    }

    public void setBoss2Name(String boss2Name) {
        this.boss2Name = boss2Name == null ? null : boss2Name.trim();
    }

    public String getHrNo() {
        return hrNo;
    }

    public void setHrNo(String hrNo) {
        this.hrNo = hrNo == null ? null : hrNo.trim();
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName == null ? null : hrName.trim();
    }
}