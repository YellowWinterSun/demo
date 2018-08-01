package com.dayi.demo.controller;

import com.dayi.demo.controller.model.MeetingRoomOrderRes;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import com.dayi.demo.model.modelEnum.MeetingRoomEnum;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.MeetingOrderService;
import com.dayi.demo.service.MeetingRoomService;
import com.dayi.demo.service.MessageService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预约会议室控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/2
 */
@Controller
@RequestMapping("/orderMeeting")
public class OrderMeetingController {

    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    private MeetingOrderService meetingOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    /**
     * 跳转“我要预约”主页
     * @param roomId 要预约的会议室id（传入空则代表没有预选会议室跳转)
     * @return
     */
    @RequestMapping("/toOrder")
    public ModelAndView toOrder(String roomId){
        ModelAndView mav = new ModelAndView("meeting/orderMeeting");

        //获取会议室列表（仅能获取可用的）
        List<MeetingRoom> listRoom = meetingRoomService.listRoom();

        //获取员工列表
        List<User> listUser = userService.listUser();

        if (StringUtils.isBlank(roomId)){
            roomId = null;
        }
        mav.addObject("listRoom", listRoom);
        mav.addObject("listUser", listUser);
        mav.addObject("roomId", roomId);

        return mav;
    }

    /**
     * 验证会议室在这个时间段，是否可预约
     * @param roomId 会议室ID
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return
     */
    @RequestMapping("/checkOrderTime")
    @ResponseBody
    public Msg checkOrderTime(String roomId, Date startTime, Date endTime){
        if (StringUtils.isBlank(roomId)){
            return Msg.error("未选择会议室");
        }
        if (null == startTime || null == endTime){
            return Msg.error("时间未选择");
        }
        //起始时间不能大于等于结束时间
        if (startTime.equals(endTime) || startTime.getTime() > endTime.getTime()){
            return Msg.res(ResStatusEnum.MEETING_TIME_ERROR);
        }
        //防止恶意传入跨日的预约申请
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format.format(startTime);
        String date2 = format.format(endTime);
        if (!date1.equals(date2)){
            return Msg.res(ResStatusEnum.MEETING_ORDER_SUPERPASS_DAY);
        }
        //验证，预约时间是否是未来的
        Date nowDate = new Date();
        long nowDatetime = nowDate.getTime();
        long startDateTime = startTime.getTime();
        long endDateTime = endTime.getTime();
        if (nowDatetime > endDateTime || (nowDatetime > startDateTime && nowDatetime <= endDateTime)){
            return Msg.error("不能预约以前的时间");
        }

        //验证预约时间是否冲突
        boolean valid = meetingOrderService.validMeetingOrder(roomId, startTime, endTime);
        if (valid){
            //无冲突
            return Msg.success(null);
        }
        return Msg.res(ResStatusEnum.MEETING_ORDER_TIME_CONFLICT);
    }

    /**
     * 预约会议室申请
     * @param roomId 会议室 ID
     * @param startTime 预约起始时间
     * @param endTime 预约结束时间
     * @param joinUserNos 参会用户工号,多个用户用，隔开
     * @param alertCheck 申请成功后，是否马上通知一次用户
     * @return
     */
    @RequestMapping("/doOrder")
    @ResponseBody
    public Msg doOrder(HttpSession session, String roomId, Date startTime, Date endTime, String joinUserNos, boolean alertCheck){
        //校验参数
        if (StringUtils.isBlank(roomId) || null == startTime || null == endTime){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }
        //验证会议室是否可用
        MeetingRoom meetingRoom = meetingRoomService.getRoomById(roomId);
        if (null == meetingRoom || meetingRoom.getStatus().equals(MeetingRoomEnum.DISABLED)){
            //会议室不存在，或不可用的时候，不可预约
            return Msg.res(ResStatusEnum.MEETING_ROOM_DISABLED);
        }

        //起始时间不能大于等于结束时间
        if (startTime.equals(endTime) || startTime.getTime() > endTime.getTime()){
            return Msg.res(ResStatusEnum.MEETING_TIME_ERROR);
        }
        //防止恶意传入跨日的预约申请
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format.format(startTime);
        String date2 = format.format(endTime);
        if (!date1.equals(date2)){
            return Msg.res(ResStatusEnum.MEETING_ORDER_SUPERPASS_DAY);
        }
        //验证，预约时间是否是未来的
        Date nowDate = new Date();
        long nowDatetime = nowDate.getTime();
        long startDateTime = startTime.getTime();
        long endDateTime = endTime.getTime();
        if (nowDatetime > endDateTime || (nowDatetime > startDateTime && nowDatetime <= endDateTime)){
            return Msg.error("不能预约以前的时间");
        }
        //验证有无勾选参会人员（目前业务需要一定要有参会人员）
        if (StringUtils.isBlank(joinUserNos)){
            return Msg.error("没有勾选参会人员");
        }

        //以上验证通过后，进行入库操作
        User user = (User) session.getAttribute("user");
        //封装新会议预约对象
        MeetingRoomOrder roomOrder = new MeetingRoomOrder();
        roomOrder.setId(UUIDUtil.getUUID());
        roomOrder.setStarttime(startTime);
        roomOrder.setEndtime(endTime);
        roomOrder.setRoomId(roomId);
        roomOrder.setStatus(MeetingOrderEnum.NORMAL.getValue());
        roomOrder.setOrderUserNo(user.getNo());
        roomOrder.setJoinUsersNo(joinUserNos.trim());

        //添加预约记录
        boolean isSuccess = meetingOrderService.addMeetingOrder(roomOrder);
        if (isSuccess){
            //预约成功后，如果需要立刻通知，则启动通知服务
            if (alertCheck){
                messageService.informMeetingJoinUser(roomOrder.getId());
            }

            return Msg.success(null);
        }
        return Msg.res(ResStatusEnum.MEETING_ORDER_TIME_CONFLICT);
    }

    /**
     * 跳转至“我的会议情况”主页
     * @return
     */
    @RequestMapping("/toMyOrderJsp")
    public String toMyOrder(){
        return "meeting/viewMyOrder";
    }

    /**
     * 获取本人相关的会议预约信息（默认按开始时间升序排序）
     * @param session
     * @return
     */
    @RequestMapping("/listMyOrder")
    @ResponseBody
    public Msg listMyOrder(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<MeetingRoomOrderRes> list = meetingOrderService.listMyOrder(user.getNo());
        return Msg.success(null).add("list", list);
    }

    /**
     * 获取会议预约信息的参会人员信息
     * @param orderId 会议预约ID
     * @return
     */
    @RequestMapping("/listOrderUserInfo")
    @ResponseBody
    public Msg listOrderUserInfo(String orderId){
        //获取会议预约记录信息
        MeetingRoomOrder order = meetingOrderService.getOrderById(orderId);

        //记录会议人员信息集合
        List<User> list = new ArrayList<>();
        //获取预约人信息
        User orderUser = userService.getUserByNo(order.getOrderUserNo());
        if (null != orderUser){
            list.add(orderUser);
        }
        //获取参会人员
        String joinUserNos = order.getJoinUsersNo();
        if (StringUtils.isNotBlank(joinUserNos)){
            for (String joinNo : joinUserNos.split(",")){
                User joinUser = userService.getUserByNo(joinNo);
                if (null != joinUser){
                    list.add(joinUser);
                }
            }
        }
        return Msg.success(null).add("list", list);
    }

    /**
     * 提醒某人参加某会议
     * @param orderId
     * @param userNo
     */
    @RequestMapping("/informUserOrderMeeting")
    @ResponseBody
    public Msg informUserOrderMeeting(String orderId, String userNo){
        //通知
        try {
            boolean result = messageService.informUserMeetingOrder(userNo, orderId);
            if (result){
                return Msg.success(null);
            }
            return Msg.error(null);
        } catch (Exception e){
            return Msg.error(null);
        }
    }

    /**
     * 取消会议预约，只能预约人本人取消
     * @param session
     * @param orderId
     * @return
     */
    @RequestMapping("/cancelMyOrder")
    @ResponseBody
    public Msg cancelMyOrder(HttpSession session, String orderId){
        //校验参数
        if (StringUtils.isBlank(orderId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }
        //获取会议预约记录
        MeetingRoomOrder order = meetingOrderService.getOrderById(orderId);
        if (null == order){
            return Msg.error("会议预约不存在");
        }
        //获取用户信息
        User user = (User) session.getAttribute("user");

        if (!user.getNo().equals(order.getOrderUserNo())){
            return Msg.error("非预约人不可取消");
        }

        if (!order.getStatus().equals(MeetingOrderEnum.NORMAL.getValue())){
            //会议非NORMAL状态，直接返回即可
            return Msg.success(null);
        }

        boolean result = meetingOrderService.cancelOrder(orderId);
        if (result){
            messageService.informMeetingJoinUserCancel(orderId);
            return Msg.success(null);
        }
        return Msg.error(null);
    }

    /**
     * 跳转“会议室预约记录”页面
     * @return
     */
    @RequestMapping("/toOrderRecordJsp")
    public String toOrderRecordJsp(){
        return "meeting/viewOrderRecord";
    }

    /**
     * 获取系统中的会议记录信息（需要权限）
     * @return
     */
    @RequestMapping("/listOrderRecord")
    @ResponseBody
    public Msg listOrderRecord(String roomId, String status, Integer limitStart, Integer limitEnd, String order, String sort){
        Map<String, Object> map = meetingOrderService.listOrderRecord(roomId, status, limitStart, limitEnd, order, sort);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 会议室预约记录界面 - 取消会议记录（需要权限）
     * @param orderId 要取消的会议记录
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public Msg cancelOrder(String orderId){
        //校验参数
        if (StringUtils.isBlank(orderId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }
        //获取会议预约记录
        MeetingRoomOrder order = meetingOrderService.getOrderById(orderId);
        if (null == order){
            return Msg.error("会议预约不存在");
        }

        if (!order.getStatus().equals(MeetingOrderEnum.NORMAL.getValue())){
            //会议非NORMAL状态，直接返回即可
            return Msg.success(null);
        }

        boolean result = meetingOrderService.cancelOrder(orderId);
        if (result){
            messageService.informMeetingJoinUserCancel(orderId);
            return Msg.success(null);
        }
        return Msg.error(null);
    }

    /**
     * 参数格式化，将前台的“yyyy-MM-dd HH:mm:ss”字符串自动转化为Date类
     * @param bin
     */
    @InitBinder("startTime")
    public void initBinderStartTime(ServletRequestDataBinder bin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }

    @InitBinder("endTime")
    public void initBinderEndTime(ServletRequestDataBinder bin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }
}
