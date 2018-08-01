package com.dayi.demo.service;

import com.dayi.demo.controller.model.MeetingRoomRes;
import com.dayi.demo.model.MeetingRoom;

import java.util.List;
import java.util.Map;

/**
 * 会议室相关服务接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
public interface MeetingRoomService {

    /**
     * 按照条件，搜索会议室。（会议室默认按名字排序，并过滤不可用的会议室）
     * @param searchText 会议室描述（模糊搜索)
     * @param name 会议室名称（模糊搜索)
     * @param needSize 需要支持的人数
     * @return
     */
    List<MeetingRoomRes> listMeetingRoomRes(String searchText, String name, Integer needSize);

    /**
     * 获取系统所有会议室（过滤不可用的，默认按名字排序)
     * @return
     */
    List<MeetingRoom> listRoom();

    /**
     * 根据条件，搜索会议室
     * @param remark 会议室描述（模糊搜索）
     * @param status 会议室状态
     * @param limitStart
     * @param limitEnd
     * @param order
     * @param sort
     * @return
     */
    Map<String, Object> listRoomLimit(String remark, String status, Integer limitStart, Integer limitEnd, String order, String sort);

    /**
     * 根据ID，获取会议室信息
     * @param roomId
     * @return
     */
    MeetingRoom getRoomById(String roomId);

    /**
     * 新增一个会议室
     * @param roomSelective
     */
    void addMeetingRoom(MeetingRoom roomSelective);

    /**
     * 根据id，更新一个会议室的信息
     * @param roomSelective
     * @return
     */
    boolean updateMeetingRoomSelective(MeetingRoom roomSelective);

    /**
     * 删除会议室
     * @param roomId
     */
    void deleteRoom(String roomId);
}
