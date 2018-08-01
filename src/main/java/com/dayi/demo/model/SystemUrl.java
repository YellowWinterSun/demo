package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库System_url表
 * 权限表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class SystemUrl {
    //UUID
    private String id;
    //起始时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //权限名称
    private String name;
    //权限地址
    private String url;
    //权限描述
    private String remark;
    //权限状态
    private String status;
    //菜单级别
    private Byte level;
    //父级功能菜单URL的ID
    private String parentId;
    //父级URL的权限地址
    private String parentUrl;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl == null ? null : parentUrl.trim();
    }
}