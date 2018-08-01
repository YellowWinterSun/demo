package com.dayi.demo.service;

/**
 * 通知服务类
 * 用于通知功能的服务类，
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
public interface MessageService {

    /**
     * 绩效考核表 完成考核通知（当绩效考核表完成部门总监考核后，发送通知给考核人查看考核结果)
     * @param officialId 绩效考核表ID
     */
    void informOfficialEvaluationUser(String officialId);

    /**
     * 通知预约成功的会议，所有参与人
     * @param orderId 会议室预约id
     */
    void informMeetingJoinUser(String orderId);

    /**
     * 会议预约取消通知，通知所有参与人
     * @param orderId 会议室预约id
     */
    void informMeetingJoinUserCancel(String orderId);

    /**
     * 提醒某用户参加某会议
     * @param userNo 要提醒的用户
     * @param orderId 要参与的会议
     */
    boolean informUserMeetingOrder(String userNo, String orderId);

    /**
     * 定时任务每5分钟执行一次改方法
     * 定时任务触发的方法，会议开始前1小时，半小时通知参会人员
     * 获取所有符合条件的会议预约记录，并通知相关人员
     */
    void informMeetingOrderStartTask();
}
