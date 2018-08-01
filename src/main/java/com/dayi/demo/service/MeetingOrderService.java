package com.dayi.demo.service;

import com.dayi.demo.controller.model.MeetingRoomOrderRes;
import com.dayi.demo.model.MeetingRoomOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约会议室服务接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/2
 */
public interface MeetingOrderService {

    /**
     * 验证预约会议室的时间是否有冲突
     * @param roomId 会议室ID
     * @param startTime 预约开始时间
     * @param endTime 预约结束时间
     * @return true无冲突， false有冲突
     */
    boolean validMeetingOrder(String roomId, Date startTime, Date endTime);

    /**
     * 新增一个会议预约记录(实现同步，防止极少数情况下，两个用户同时增加预约冲突的时间)
     * @param roomOrder 新的会议预约记录
     * @return 成功返回true，如果预约时间冲突返回false。
     */
    boolean addMeetingOrder(MeetingRoomOrder roomOrder);

    /**
     * 获取某个用户的会议预约情况（可用且默认按会议开始时间升序排序)
     * @param userNo
     * @return
     */
    List<MeetingRoomOrderRes> listMyOrder(String userNo);

    /**
     * 获取系统中所有会议预约记录（默认按结束时间降序排序）
     * @param roomId 会议室ID
     * @param  status 会议预约状态
     * @param limitStart
     * @param limitEnd
     * @param order
     * @param sort
     * @return
     */
    Map<String, Object> listOrderRecord(String roomId, String status, Integer limitStart, Integer limitEnd, String order, String sort);

    /**
     * 统计某个会议室，当前预约中的预约数
     * @param roomId
     * @return
     */
    int countMeetingRoomExistsOrder(String roomId);

    /**
     * 根据id获取会议预约记录信息
     * @param id UUID
     * @return
     */
    MeetingRoomOrder getOrderById(String id);

    /**
     * 取消会议预约，将NORMAL改为CANCEL
     * @param orderId 要需要的会议预约
     * @return
     */
    boolean cancelOrder(String orderId);

    /**
     * 将所有过期的会议预约状态，从NORMAL改为OVERDUE
     */
    void cleanOverdueOrder();

    /**
     * 删除所有取消的会议记录
     */
    void deleteCancelOrder();

    /**
     * 删除所有已结束（已过期）的会议记录
     */
    void deleteOverdueOrder();
}
