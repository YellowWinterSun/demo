package com.dayi.demo.controller.model;

import com.dayi.demo.model.SystemUrl;

/**
 * 权限返回类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/9
 */
public class SystemUrlRes extends SystemUrl {
    //父级URL名称
    private String parentUrlName;

    public SystemUrlRes(SystemUrl source, String parentUrlName){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setName(source.getName());
        this.setUrl(source.getUrl());
        this.setRemark(source.getRemark());
        this.setStatus(source.getStatus());
        this.setLevel(source.getLevel());
        this.setParentId(source.getParentId());
        this.setParentUrl(source.getParentUrl());

        this.parentUrlName = parentUrlName;
    }

    public String getParentUrlName() {
        return parentUrlName;
    }

    public void setParentUrlName(String parentUrlName) {
        this.parentUrlName = parentUrlName;
    }
}
