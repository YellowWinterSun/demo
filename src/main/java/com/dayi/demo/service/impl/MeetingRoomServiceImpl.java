package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.MeetingRoomRes;
import com.dayi.demo.dao.MeetingOrderDao;
import com.dayi.demo.dao.MeetingRoomDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomExample;
import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import com.dayi.demo.model.modelEnum.MeetingRoomEnum;
import com.dayi.demo.service.MeetingRoomService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会议室相关服务实现类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomDao meetingRoomDao;
    @Autowired
    private MeetingOrderDao meetingOrderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<MeetingRoomRes> listMeetingRoomRes(String searchText, String name, Integer needSize) {
        //获取公司会议室信息
        List<MeetingRoom> list = meetingRoomDao.list(searchText, name, needSize, MeetingRoomEnum.NORMAL.getValue());

        //日期格式化工具
        SimpleDateFormat formatUtil = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatHmUtil = new SimpleDateFormat("HH:mm");

        //二次封装会议室信息
        List<MeetingRoomRes> listResult = new ArrayList<>();
        for (MeetingRoom room : list){
            //获取会议室预约记录
            List<MeetingRoomOrder> listOrder = meetingOrderDao.list(room.getId(), MeetingOrderEnum.NORMAL.getValue());

            //记录会议室预约记录字符串 格式： 谁谁谁 yyyy-mm-dd hh:MM:ss 至 hh:MM:ss
            List<String> listOrderTimes = new ArrayList<>();
            for (MeetingRoomOrder order : listOrder){
                //获取预约人信息
                User user = userDao.getUserByNo(order.getOrderUserNo());

                String str = user.getName() + " " + formatUtil.format(order.getStarttime()) + " 至 " + formatHmUtil.format(order.getEndtime());
                listOrderTimes.add(str);
            }

            MeetingRoomRes res = new MeetingRoomRes(room, listOrder.size(), listOrderTimes);
            listResult.add(res);
        }

        return listResult;
    }

    @Override
    public List<MeetingRoom> listRoom() {

        return meetingRoomDao.list(null, null, null, MeetingRoomEnum.NORMAL.getValue());
    }

    @Override
    public Map<String, Object> listRoomLimit(String remark, String status, Integer limitStart, Integer limitEnd, String order, String sort) {
        //封装条件
        MeetingRoomExample example = new MeetingRoomExample();
        MeetingRoomExample.Criteria c = example.createCriteria();

        if (StringUtils.isNotBlank(remark)){
            c.andRemarkLike("%" + remark + "%");
        }
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        //获取分页的结果集合
        List<MeetingRoom> list = meetingRoomDao.listLimit(example, limitStart, limitEnd);
        //统计不分页情况下的集合数量
        Integer total = 0;
        if (null == limitStart){
            total = list.size();
        } else {
            total = meetingRoomDao.countByExample(example);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("total", total);
        resultMap.put("rows", list.size());

        return resultMap;
    }

    @Override
    public MeetingRoom getRoomById(String roomId) {
        if (StringUtils.isBlank(roomId)){
            return null;
        }
        return meetingRoomDao.getById(roomId);
    }

    @Override
    public void addMeetingRoom(MeetingRoom roomSelective) throws RuntimeException{
        if (StringUtils.isBlank(roomSelective.getId())){
            roomSelective.setId(UUIDUtil.getUUID());
        }
        if (StringUtils.isBlank(roomSelective.getStatus())){
            roomSelective.setStatus(MeetingRoomEnum.NORMAL.getValue());
        }
        meetingRoomDao.addMeetingRoom(roomSelective);
        return;
    }

    @Override
    public boolean updateMeetingRoomSelective(MeetingRoom roomSelective) {
        if (null == roomSelective.getUpdatetime()){
            roomSelective.setUpdatetime(new Date());
        }

        int num = meetingRoomDao.updateRoom(roomSelective);
        return (num > 0) ? true : false;
    }

    @Override
    public void deleteRoom(String roomId) throws RuntimeException{
        meetingRoomDao.deleteRoom(roomId);
        meetingOrderDao.deleteByRoomId(roomId);
        return;
    }
}
