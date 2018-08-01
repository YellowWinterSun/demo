package com.dayi.demo.model;

import java.util.Date;

/**
 * 数据库MeetingRoomOrder表
 * 会议室预约记录表
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class MeetingRoomOrder {
    //UUID
    private String id;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //会议起始时间
    private Date starttime;
    //会议结束时间
    private Date endtime;
    //预约人工号
    private String orderUserNo;
    //参会人员工号集（多个参会人员用逗号隔开）
    private String joinUsersNo;
    //会议室ID
    private String roomId;
    //预约状态
    private String status;

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

    public String getOrderUserNo() {
        return orderUserNo;
    }

    public void setOrderUserNo(String orderUserNo) {
        this.orderUserNo = orderUserNo == null ? null : orderUserNo.trim();
    }

    public String getJoinUsersNo() {
        return joinUsersNo;
    }

    public void setJoinUsersNo(String joinUsersNo) {
        this.joinUsersNo = joinUsersNo == null ? null : joinUsersNo.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}