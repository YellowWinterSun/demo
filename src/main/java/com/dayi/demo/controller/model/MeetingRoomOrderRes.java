package com.dayi.demo.controller.model;


import com.dayi.demo.model.MeetingRoomOrder;

import java.util.Date;

/**
 * 会议预约记录返回体
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/3
 */
public class MeetingRoomOrderRes {
    //会议室名称
    private String roomName;
    //会议预约记录ID
    private String orderId;
    //会议起始时间
    private Date starttime;
    //会议结束时间
    private Date endtime;
    //预约人姓名
    private String orderName;
    //参会人姓名字符串（逗号隔开）
    private String joinUserNames;
    //会议预约状态
    private String status;


    public MeetingRoomOrderRes(MeetingRoomOrder source){
        this.orderId = source.getId();
        this.starttime = source.getStarttime();
        this.endtime = source.getEndtime();
        this.status = source.getStatus();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getJoinUserNames() {
        return joinUserNames;
    }

    public void setJoinUserNames(String joinUserNames) {
        this.joinUserNames = joinUserNames;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
