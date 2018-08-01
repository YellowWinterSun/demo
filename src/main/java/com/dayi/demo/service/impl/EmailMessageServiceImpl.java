package com.dayi.demo.service.impl;

import com.dayi.demo.dao.MeetingOrderDao;
import com.dayi.demo.dao.MeetingRoomDao;
import com.dayi.demo.dao.OfficialPerformanceEvaluationDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import com.dayi.demo.service.MessageService;
import com.dayi.demo.util.EmailUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通知服务类的实现（电子邮件方式）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Service
@Primary
public class EmailMessageServiceImpl implements MessageService {

    //邮件发送工具类
    @Autowired
    private EmailUtil emailUtil;

    //Dao
    @Autowired
    private OfficialPerformanceEvaluationDao officialPerformanceEvaluationDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MeetingOrderDao meetingOrderDao;
    @Autowired
    private MeetingRoomDao meetingRoomDao;

    //Log日志
    private static Logger logger = LoggerFactory.getLogger(EmailMessageServiceImpl.class);

    //日期格式化工具
    private SimpleDateFormat formatUtil = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat formatHmUtil = new SimpleDateFormat("HH:mm");

    /**
     * 绩效考核表 完成考核通知（当绩效考核表完成部门总监考核后，发送通知给考核人查看考核结果)
     *
     * @param officialId 绩效考核表ID
     */
    @Override
    public void informOfficialEvaluationUser(String officialId) {
        //获取绩效考核表信息
        OfficialPerformanceEvaluation officialEvaluation = officialPerformanceEvaluationDao.getById(officialId);
        if (null == officialEvaluation){
            return;
        }
        //获取用户信息
        User user = userDao.getUserByNo(officialEvaluation.getUserNo());
        if (null == user){
            return;
        }

        //获取通知人的邮箱
        String email = user.getEmail();
        //组合绩效考核表名称
        String officialName = officialEvaluation.getYear() + "年" + officialEvaluation.getMonth() +
                "月 " + officialEvaluation.getJobName() + "绩效考核表";

        //获取的是服务器的IP地址
        InetAddress address = null;
        String hostAddress = null;
        try {
            address = InetAddress.getLocalHost();
            hostAddress = address.getHostAddress() + ":8080/login";
        } catch (UnknownHostException e) {
            hostAddress = "javascript:void(0);";
        }

        //组合正文内容
        String content = "<b>" + user.getName() + "</b>,您好<br/>" +
                "您本月的绩效考核：" + officialName + "。部门总监已审核提交，请登录<b onclick=\"window.location.href='" + hostAddress + "'\">绩效考核系统</b>查看你的结果。";

        emailUtil.sendPictureMails(officialName + "已更新，请查收", content, email, "static/image/monda_logo.png");
    }

    @Override
    public void informMeetingJoinUser(String orderId) {
        MeetingRoomOrder roomOrder = meetingOrderDao.getById(orderId);
        if (null == roomOrder || !roomOrder.getStatus().equals(MeetingOrderEnum.NORMAL.getValue())){
            //没有会议预约记录
            return;
        }
        //获取会议室信息
        MeetingRoom meetingRoom = meetingRoomDao.getById(roomOrder.getRoomId());
        if (null == meetingRoom){
            return;
        }

        //获取预约人的信息
        User orderUser = userDao.getUserByNo(roomOrder.getOrderUserNo());

        //记录要通知的用户邮箱
        List<String> listUserEmails = new ArrayList<>();
        if (null != orderUser && StringUtils.isNotBlank(orderUser.getEmail())) {
            //预约人存在，且存在邮箱信息
            listUserEmails.add(orderUser.getEmail());
        }

        //记录参会人邮箱
        String userNos = roomOrder.getJoinUsersNo();
        if (StringUtils.isNotBlank(userNos)){
            //有参会人员
            for (String userNo : userNos.split(",")){
                User joinUser = userDao.getUserByNo(userNo);
                if (null != joinUser && StringUtils.isNotBlank(joinUser.getEmail())){
                    //参会人存在，且存在邮箱信息
                    listUserEmails.add(joinUser.getEmail());
                }
            }
        }

        if (listUserEmails.isEmpty()){
            //要通知的邮箱是空
            return ;
        }

        String emailStr = StringUtils.join(listUserEmails.toArray(), ",");
        System.out.println("要发送的邮箱:" + emailStr);

        //组装邮件内容
        String subject = formatUtil.format(roomOrder.getStarttime()) + "开始的会议通知";
        String content = "<p>会议时间: " + formatUtil.format(roomOrder.getStarttime()) + "至" + formatHmUtil.format(roomOrder.getEndtime()) + "</p>"
                + "<p>会议室: " + meetingRoom.getName() + "</p>"
                + "<p>预约人: " + orderUser.getName() + "</p>"
                + "<p>手机号码: " + orderUser.getPhone() + "</p>";

        emailUtil.sendPictureMails(subject, content, emailStr, "static/image/monda_logo.png");
    }

    @Override
    public void informMeetingJoinUserCancel(String orderId) {
        MeetingRoomOrder roomOrder = meetingOrderDao.getById(orderId);
        if (null == roomOrder || roomOrder.getStatus().equals(MeetingOrderEnum.NORMAL.getValue())){
            //没有会议预约记录
            return;
        }
        //获取会议室信息
        MeetingRoom meetingRoom = meetingRoomDao.getById(roomOrder.getRoomId());
        if (null == meetingRoom){
            return;
        }

        //获取预约人的信息
        User orderUser = userDao.getUserByNo(roomOrder.getOrderUserNo());

        //记录要通知的用户邮箱
        List<String> listUserEmails = new ArrayList<>();
        if (null != orderUser && StringUtils.isNotBlank(orderUser.getEmail())) {
            //预约人存在，且存在邮箱信息
            listUserEmails.add(orderUser.getEmail());
        }

        //记录参会人邮箱
        String userNos = roomOrder.getJoinUsersNo();
        if (StringUtils.isNotBlank(userNos)){
            //有参会人员
            for (String userNo : userNos.split(",")){
                User joinUser = userDao.getUserByNo(userNo);
                if (null != joinUser && StringUtils.isNotBlank(joinUser.getEmail())){
                    //参会人存在，且存在邮箱信息
                    listUserEmails.add(joinUser.getEmail());
                }
            }
        }

        if (listUserEmails.isEmpty()){
            //要通知的邮箱是空
            return ;
        }

        String emailStr = StringUtils.join(listUserEmails.toArray(), ",");
        System.out.println("要发送的邮箱:" + emailStr);

        //组装邮件内容
        String subject = formatUtil.format(roomOrder.getStarttime()) + "开始的会议取消通知";
        String content = "<b style='color:red;'>会议取消通知！</b><p>会议时间: " + formatUtil.format(roomOrder.getStarttime()) + "至" + formatHmUtil.format(roomOrder.getEndtime()) + "</p>"
                + "<p>会议室: " + meetingRoom.getName() + "</p>"
                + "<p>预约人: " + orderUser.getName() + "</p>"
                + "<p>手机号码: " + orderUser.getPhone() + "</p>";

        emailUtil.sendPictureMails(subject, content, emailStr, "static/image/monda_logo.png");
    }

    @Override
    public boolean informUserMeetingOrder(String userNo, String orderId) {
        //获取会议预约信息
        MeetingRoomOrder order = meetingOrderDao.getById(orderId);
        if (null == order || !order.getStatus().equals(MeetingOrderEnum.NORMAL.getValue())){
            //会议预约不存在，或会议预约不是正常状态
            return false;
        }
        //判断要通知的用户，是不是本次会议的
        String validOrderNo = order.getOrderUserNo();
        String validJoinNos = order.getJoinUsersNo();
        boolean valid = false;
        if (null != validOrderNo && validOrderNo.equals(userNo)){
            //说明当前用户是预约人
            valid = true;
        }
        if (null != validJoinNos && validJoinNos.indexOf(userNo) >= 0){
            //说明当前用户是参会人员
            valid = true;
        }
        if (!valid){
            //不通过验证
            return false;
        }

        //获取用户信息
        User user = userDao.getUserByNo(userNo);
        if (null == user || StringUtils.isBlank(user.getEmail())){
            //用户不存在或用户邮箱不存在
            return false;
        }

        //获取会议室信息
        MeetingRoom meetingRoom = meetingRoomDao.getById(order.getRoomId());
        if (null == meetingRoom){
            return false;
        }

        //组装邮件内容
        String subject = formatUtil.format(order.getStarttime()) + "开始的会议通知";
        String content = "<p>会议时间: " + formatUtil.format(order.getStarttime()) + "至" + formatHmUtil.format(order.getEndtime()) + "</p>"
                + "<p>会议室: " + meetingRoom.getName() + "</p>"
                + "<p>预约人: " + user.getName() + "</p>"
                + "<p>手机号码: " + user.getPhone() + "</p>";

        emailUtil.sendPictureMails(subject, content, user.getEmail(), "static/image/monda_logo.png");
        return true;
    }

    @Override
    public void informMeetingOrderStartTask() {
        //获取现在时间
        Date nowDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            /*数据库中的日期数据类型为TimeStamp，因此如果直接传入Date，会导致yyyy-MM-dd HH:mm:ss.008（秒后面还会有精度)，
            不需要这个精度，并且存在这个精度时，会导致一些日期大小判断有问题。因此需要格式化日期，去除尾巴的精度
             */
            nowDate = format.parse(format.format(nowDate));
        } catch (ParseException e) {
            logger.info("定时任务，会议开始前邮件通知，日期转化发生错误:" + e.getMessage());
        }

        //符合会议开始前1小时的时间段
        //定义符合会议开始前1小时时间段的左闭区间 [left,right)
        Calendar hour1OrderLeft = Calendar.getInstance();
        hour1OrderLeft.setTime(nowDate);
        hour1OrderLeft.add(Calendar.HOUR_OF_DAY, 1);

        //定义符合开始前1小时的右开区间 [left,right)
        Calendar hour1OrderRight = Calendar.getInstance();
        hour1OrderRight.setTime(nowDate);
        hour1OrderRight.add(Calendar.HOUR_OF_DAY, 1);
        hour1OrderRight.add(Calendar.MINUTE, 5);

        System.out.println(hour1OrderLeft.getTime() + "," + hour1OrderRight.getTime());

        //符合会议开始前1小时邮件通知的会议
        List<MeetingRoomOrder> list1 = meetingOrderDao.listRoomOrderBetweenTime(hour1OrderLeft.getTime(), hour1OrderRight.getTime());

        //符合会议开始前半小时的时间段 [left, right)
        Calendar min30OrderLeft = Calendar.getInstance();
        min30OrderLeft.setTime(nowDate);
        min30OrderLeft.add(Calendar.MINUTE,30);

        Calendar min30OrderRight = Calendar.getInstance();
        min30OrderRight.setTime(nowDate);
        min30OrderRight.add(Calendar.MINUTE,35);

        //符合会议开始前半小时的会议
        List<MeetingRoomOrder> list2 = meetingOrderDao.listRoomOrderBetweenTime(min30OrderLeft.getTime(), min30OrderRight.getTime());

        //符合开会前1小时的会议预约记录
        for (MeetingRoomOrder order : list1){
            this.informMeetingJoinUser(order.getId());
        }

        //符合开会前半小时的会议预约记录
        for (MeetingRoomOrder order : list2){
            this.informMeetingJoinUser(order.getId());
        }

        return;
    }


}
