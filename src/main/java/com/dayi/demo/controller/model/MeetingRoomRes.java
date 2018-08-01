package com.dayi.demo.controller.model;

import com.dayi.demo.model.MeetingRoom;

import java.util.List;

/**
 * 会议室返回类（二次封装）
 * 用于前端展示会议室信息
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
public class MeetingRoomRes extends MeetingRoom {
    //预约情况（已预约成功的数量）
    private Integer orderNum;
    //未进行的，预约成功的预约会议室时间。格式： 谁谁谁 yyyy-mm-dd hh:MM:ss 至 hh:MM:ss
    private List<String> orderTimes;

    //构造方法
    public MeetingRoomRes(MeetingRoom source, Integer orderNum, List<String> orderTimes){
        this.setId(source.getId());
        this.setCreatetime(source.getCreatetime());
        this.setUpdatetime(source.getUpdatetime());
        this.setName(source.getName());
        this.setSize(source.getSize());
        this.setPath(source.getPath());
        this.setRemark(source.getRemark());
        this.setStatus(source.getStatus());

        this.orderNum = orderNum;
        this.orderTimes = orderTimes;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<String> getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(List<String> orderTimes) {
        this.orderTimes = orderTimes;
    }
}
