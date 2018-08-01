package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.MeetingRoomOrderRes;
import com.dayi.demo.dao.MeetingOrderDao;
import com.dayi.demo.dao.MeetingRoomDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.MeetingRoomOrderExample;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import com.dayi.demo.service.MeetingOrderService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 预约会议室服务实现类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/2
 */
@Service
public class MeetingOrderServiceImpl implements MeetingOrderService {

    @Autowired
    private MeetingOrderDao meetingOrderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MeetingRoomDao meetingRoomDao;

    /**
     * 验证预约会议室的时间是否有冲突
     *
     * @param roomId    会议室ID
     * @param startTime 预约开始时间
     * @param endTime   预约结束时间
     * @return true无冲突， false有冲突
     */
    @Override
    public boolean validMeetingOrder(String roomId, Date startTime, Date endTime) {
        //验证预约时间冲突的第一种情况（要预约的时间区间，冲突了已预约时间的起始时间点）
        boolean valid1 = meetingOrderDao.validOrderStartTime(roomId, startTime, endTime);
        if (!valid1){
            return false;
        }
        //验证预约时间冲突的第二种情况（要预约的时间区间，在已预约的时间区间内）
        boolean valid2 = meetingOrderDao.validOrderBetweenTime(roomId, startTime, endTime);
        if (!valid2){
            return false;
        }
        //验证预约时间冲突的第三种情况（要预约的时间区间，冲突了已预约时间的结束时间点)
        boolean valid3 = meetingOrderDao.validOrderEndTime(roomId, startTime, endTime);
        if (!valid3){
            return false;
        }

        return valid1 && valid2 && valid3;
    }

    @Override
    public synchronized boolean addMeetingOrder(MeetingRoomOrder roomOrder) {
        boolean valid = this.validMeetingOrder(roomOrder.getRoomId(), roomOrder.getStarttime(), roomOrder.getEndtime());
        if (!valid){
            return false;
        }

        if (null == roomOrder.getId()){
            roomOrder.setId(UUIDUtil.getUUID());
        }

        int num = meetingOrderDao.addMeetingOrder(roomOrder);
        return (num > 0) ? true : false;
    }

    @Override
    public List<MeetingRoomOrderRes> listMyOrder(String userNo) {

        if (StringUtils.isBlank(userNo)) {
            return new ArrayList<>();
        }
        //获取符合条件的预约记录
        List<MeetingRoomOrder> list = meetingOrderDao.listUserOrder(userNo);
        if (list.isEmpty()){
            return new ArrayList<>();
        }
        //二次封装会议预约记录信息体
        List<MeetingRoomOrderRes> listResult = new ArrayList<>();
        for (MeetingRoomOrder order : list){
            MeetingRoomOrderRes res = new MeetingRoomOrderRes(order);
            //获取会议室名字信息
            MeetingRoom room = meetingRoomDao.getById(order.getRoomId());
            String roomName = room.getName();
            //获取预约人信息
            User orderUser = userDao.getUserByNo(order.getOrderUserNo());
            String orderName = (null == orderUser) ? "" : orderUser.getName();
            //获取参会人员信息
            List<String> listJoinUserName = new ArrayList<>();
            if (StringUtils.isNotBlank(order.getJoinUsersNo())){
                for (String joinUserNo : order.getJoinUsersNo().split(",")){
                    //获取参会人信息
                    User joinUser = userDao.getUserByNo(joinUserNo);
                    String joinName = (null == joinUser) ? "" : joinUser.getName();
                    listJoinUserName.add(joinName);
                }
            }
            String joinUserNames = "";
            if (!listJoinUserName.isEmpty()){
                joinUserNames = StringUtils.join(listJoinUserName.toArray(), ",");
            }

            res.setRoomName(roomName);
            res.setOrderName(orderName);
            res.setJoinUserNames(joinUserNames);

            listResult.add(res);
        }
        return listResult;
    }

    @Override
    public Map<String, Object> listOrderRecord(String roomId, String status, Integer limitStart, Integer limitEnd, String order, String sort) {
        //封装条件
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();

        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(roomId)){
            c.andRoomIdEqualTo(roomId);
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        //获取分页的结果集合
        List<MeetingRoomOrder> list = meetingOrderDao.listRoomOrderLimit(example, limitStart, limitEnd);
        //统计不分页情况的匹配记录数
        Integer total = 0;
        if (null == limitStart){
            total = list.size();
        } else {
            total = meetingOrderDao.countByExample(example);
        }

        //二次封装
        List<MeetingRoomOrderRes> listRes = new ArrayList<>();
        for (MeetingRoomOrder roomOrder : list){
            MeetingRoomOrderRes res = new MeetingRoomOrderRes(roomOrder);
            //获取会议室名字信息
            MeetingRoom room = meetingRoomDao.getById(roomOrder.getRoomId());
            String roomName = room.getName();
            //获取预约人信息
            User orderUser = userDao.getUserByNo(roomOrder.getOrderUserNo());
            String orderName = (null == orderUser) ? "" : orderUser.getName();
            //获取参会人员信息
            List<String> listJoinUserName = new ArrayList<>();
            if (StringUtils.isNotBlank(roomOrder.getJoinUsersNo())){
                for (String joinUserNo : roomOrder.getJoinUsersNo().split(",")){
                    //获取参会人信息
                    User joinUser = userDao.getUserByNo(joinUserNo);
                    String joinName = (null == joinUser) ? "" : joinUser.getName();
                    listJoinUserName.add(joinName);
                }
            }
            String joinUserNames = "";
            if (!listJoinUserName.isEmpty()){
                joinUserNames = StringUtils.join(listJoinUserName.toArray(), ",");
            }

            res.setRoomName(roomName);
            res.setOrderName(orderName);
            res.setJoinUserNames(joinUserNames);

            listRes.add(res);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", listRes);
        resultMap.put("total", total);
        resultMap.put("rows", listRes.size());

        return resultMap;

    }

    @Override
    public int countMeetingRoomExistsOrder(String roomId) {
        if (StringUtils.isBlank(roomId)){
            return 0;
        }
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andRoomIdEqualTo(roomId);
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());

        return meetingOrderDao.countByExample(example);
    }

    @Override
    public MeetingRoomOrder getOrderById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return meetingOrderDao.getById(id);
    }

    @Override
    public boolean cancelOrder(String orderId) {
        int num = meetingOrderDao.updateOrderStatus(orderId, MeetingOrderEnum.CANCEL.getValue());
        return (num > 0) ? true : false;
    }

    @Override
    public void cleanOverdueOrder() {
        //获取现在时间
        Date nowDate = new Date();
        meetingOrderDao.updateStatusToOverdueByEndtime(nowDate);
        return;
    }

    @Override
    public void deleteCancelOrder() {
        meetingOrderDao.deleteByStatus(MeetingOrderEnum.CANCEL.getValue());
        return;
    }

    @Override
    public void deleteOverdueOrder() {
        meetingOrderDao.deleteByStatus(MeetingOrderEnum.OVERDUE.getValue());
        return;
    }

}
