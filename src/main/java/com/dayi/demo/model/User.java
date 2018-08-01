package com.dayi.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 数据库user表
 * 用户表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class User {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //姓名
    private String name;
    //密码
    private String password;
    //手机号码
    private String phone;
    //工号
    private String no;
    //企业邮箱
    private String email;
    //性别
    private String sex;
    //入职日期
    private Date entrydate;
    //直属上司工号
    private String directSupervisorNo;
    //岗位id
    private String jobId;
    //岗位名称
    private String jobName;
    //部门名称
    private String departmentName;

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public String getDirectSupervisorNo() {
        return directSupervisorNo;
    }

    public void setDirectSupervisorNo(String directSupervisorNo) {
        this.directSupervisorNo = directSupervisorNo == null ? null : directSupervisorNo.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }
}