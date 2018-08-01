package com.dayi.demo.controller;

import com.dayi.demo.controller.model.MeetingRoomRes;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import com.dayi.demo.model.modelEnum.MeetingRoomEnum;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.MeetingOrderService;
import com.dayi.demo.service.MeetingRoomService;
import com.dayi.demo.util.Msg;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会议室 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Controller
@RequestMapping("/meeting")
public class MeetingRoomController {

    //定义项目本地文件系统的根路径
    private final String systemRootPath = "E:/JettyPathRoot/demo/";

    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    private MeetingOrderService meetingOrderService;

    /**
     * 跳转“会议室一览”页面
     * @return
     */
    @RequestMapping("/toRoomShowJsp")
    public String toRoomShowJsp(){
        return "meeting/meetingRoomShow";
    }

    /**
     * 会议室一览 - 返回会议室信息列表
     * @param searchText 搜索会议室描述（模糊搜索）
     * @param name 搜索会议室名称（模糊搜索）
     * @param needSize 需要会议室支持的人数
     * @return
     */
    @RequestMapping("/listMeetingRoom")
    @ResponseBody
    public Msg listMeetingRoom(String searchText, String name, Integer needSize){
        List<MeetingRoomRes> list = meetingRoomService.listMeetingRoomRes(searchText, name, needSize);
        return Msg.success(null).add("list", list);
    }

    /**
     * 跳转“会议室管理”主界面
     * @return
     */
    @RequestMapping("/toRoomManageJsp")
    public String toRoomManageJsp(){
        return "meeting/meetingRoomManage";
    }

    /**
     * 会议室管理 - 获取会议室集合信息 (需要权限)
     * @param remark 会议室描述（模糊搜索）
     * @param status 会议室状态
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    @RequestMapping("/listRoomLimit")
    @ResponseBody
    public Msg listRoomLimit(String remark, String status, Integer limitStart, Integer limitEnd, String order, String sort){
        Map<String, Object> map = meetingRoomService.listRoomLimit(remark, status, limitStart, limitEnd, order, sort);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 会议室管理模块 - 新增会议室（上传自己的图片)
     * @param file 会议室图片（建议3：2)
     * @param name
     * @param remark
     * @param size
     * @return
     */
    @RequestMapping("/addRoomWithImg")
    @ResponseBody
    public Msg addRoomWithImg(@RequestParam("fileimg") MultipartFile file, @RequestParam("name") String name,
                              @RequestParam("remark") String remark, @RequestParam("size") Integer size){
        //校验参数
        if (StringUtils.isBlank(name) || StringUtils.isBlank(remark) || null == size){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        //判断数字是否在Short范围内
        if (size <= 0){
            return Msg.error("可容纳人数不能为空或负数");
        }
        if (size > Short.MAX_VALUE){
            return Msg.res(ResStatusEnum.MEETING_ROOM_SIZE_OVERBIG);
        }

        //为会议室获取一个UUID
        String uuid = UUIDUtil.getUUID();

        //(1)将文件流转化为文件实体
        //获取文件后缀
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        File newFile = new File(systemRootPath + uuid + suffix);
        if (newFile.exists()){
            //文件名重复
            return Msg.error("新建失败，出现id重复的情况");
        }

        try {
            file.transferTo(newFile);//将文件流转化为实体
        } catch (Exception e){
            return Msg.error("新建失败，图片写入系统失败");
        }

        //（2）封装会议室数据
        MeetingRoom newRoom = new MeetingRoom();
        newRoom.setId(uuid);
        newRoom.setName(name);
        newRoom.setSize(size.shortValue());
        newRoom.setRemark(remark);
        newRoom.setStatus(MeetingRoomEnum.NORMAL.getValue());
        newRoom.setPath(newFile.getName());

        try {
            meetingRoomService.addMeetingRoom(newRoom);
            return Msg.success(null);
        } catch (Exception e){
            //插入数据库失败，则删除上传的文件
            if (newFile.exists()){
                boolean deleteResult = false;
                int count = 0;
                while (!deleteResult && count < 10){
                    System.gc();
                    deleteResult = newFile.delete();
                    count++;
                }
            }
            return Msg.error("新建失败");
        }
    }

    /**
     * 会议室管理模块 - 新增会议室（使用系统默认的图片)
     * @param name
     * @param remark
     * @param size
     * @return
     */
    @RequestMapping("/addRoomWithDefaultImg")
    @ResponseBody
    public Msg addRoomWithDefaultImg(String name, String remark, Integer size){
        //校验参数
        if (StringUtils.isBlank(name) || StringUtils.isBlank(remark) || null == size){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        //判断数字是否在Short范围内
        if (size <= 0){
            return Msg.error("可容纳人数不能为空或负数");
        }
        if (size > Short.MAX_VALUE){
            return Msg.res(ResStatusEnum.MEETING_ROOM_SIZE_OVERBIG);
        }

        //为会议室获取一个UUID
        String uuid = UUIDUtil.getUUID();

        //（2）封装会议室数据
        MeetingRoom newRoom = new MeetingRoom();
        newRoom.setId(uuid);
        newRoom.setName(name);
        newRoom.setSize(size.shortValue());
        newRoom.setRemark(remark);
        newRoom.setStatus(MeetingRoomEnum.NORMAL.getValue());
        newRoom.setPath("meetingroom-default.jpg");

        try {
            meetingRoomService.addMeetingRoom(newRoom);
            return Msg.success(null);
        } catch (Exception e){
            return Msg.error("新建失败");
        }
    }

    /**
     * 更新会议室信息（可更新会议室名称，容纳人数以及会议室描述）(配合x-editable使用的控制器)
     * @param roomId 要更新的会议室id
     * @param field 要更新的字段
     * @param newValue 新的内容
     * @return
     */
    @RequestMapping("/updateMeetingRoomField")
    @ResponseBody
    public Msg updateMeetingRoomField(String roomId, String field, String newValue){
        //校验参数
        if (StringUtils.isBlank(roomId) || StringUtils.isBlank(field) || StringUtils.isBlank(newValue)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        //封装更新对象
        MeetingRoom updateSelective = new MeetingRoom();
        updateSelective.setId(roomId);

        //判断要修改的是哪个字段
        if (field.equals("name")){
            updateSelective.setName(newValue);
        }
        else if (field.equals("size")){
            //先判断数字是否在byte范围内
            Integer newSize = Integer.parseInt(newValue);
            //判断数字是否在Short范围内
            if (newSize <= 0){
                return Msg.error("可容纳人数不能为空或负数");
            }
            if (newSize > Short.MAX_VALUE){
                return Msg.res(ResStatusEnum.MEETING_ROOM_SIZE_OVERBIG);
            }
            updateSelective.setSize(newSize.shortValue());
        }
        else if(field.equals("remark")){
            updateSelective.setRemark(newValue);
        }

        boolean result = meetingRoomService.updateMeetingRoomSelective(updateSelective);
        if (result){
            return Msg.success(null);
        }

        return Msg.error(null);
    }

    /**
     * 更新会议室状态
     * @param roomId 要更新的会议室id
     * @return
     */
    @RequestMapping("updateRoomStatus")
    @ResponseBody
    public Msg updateRoomStatus(String roomId){
        //校验参数
        if (StringUtils.isBlank(roomId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //封装更新对象
        MeetingRoom room = meetingRoomService.getRoomById(roomId);
        if (null == room){
            return Msg.error("会议室不存在");
        }
        if (MeetingRoomEnum.NORMAL.getValue().equals(room.getStatus())){
            //当前会议室为正常状态，改为禁用
            room.setStatus(MeetingRoomEnum.DISABLED.getValue());
        } else {
            room.setStatus(MeetingRoomEnum.NORMAL.getValue());
        }

        room.setUpdatetime(new Date());
        boolean result = meetingRoomService.updateMeetingRoomSelective(room);
        if (result){
            return Msg.success(null);
        }
        return Msg.error(null);
    }

    /**
     * 更新会议室的图片
     * @param file 新的图片文件
     * @param roomId 要更新的会议室id
     * @return
     */
    @RequestMapping("updateRoomImg")
    @ResponseBody
    public Msg updateRoomImg(@RequestParam("fileimg2") MultipartFile file, @RequestParam("roomId") String roomId){
        //获取会议室对象
        MeetingRoom room = meetingRoomService.getRoomById(roomId);
        if (null == room){
            Msg.error("不存在会议室");
        }

        //删除原来的文件
        String oldPath = systemRootPath + room.getPath();
        File oldFile = new File(oldPath);
        if (oldFile.exists()){
            boolean deleteResult = false;
            int count = 0;
            while (!deleteResult && count < 10){
                System.gc();
                deleteResult = oldFile.delete();
                count++;
            }
        }

        //获取文件后缀
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")); //.jpg .png etc..

        //将新的文件写入
        File newFile = new File(systemRootPath + room.getId() + suffix);
        try {
            file.transferTo(newFile);//将文件流转化为实体
        } catch (Exception e){
            return Msg.error("更新失败，图片写入系统失败");
        }

        //更新数据库
        room.setPath(newFile.getName());
        room.setUpdatetime(new Date());

        if (meetingRoomService.updateMeetingRoomSelective(room)){
            return Msg.success(null);
        }
        return Msg.error(null);
    }

    /**
     * 会议室管理 - 删除会议室
     * @param roomId
     * @return
     */
    @RequestMapping("/deleteRoom")
    @ResponseBody
    public Msg deleteRoom(String roomId){
        if (StringUtils.isBlank(roomId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //获取会议室信息
        MeetingRoom room = meetingRoomService.getRoomById(roomId);
        if (null == room){
            return Msg.error("会议室不存在");
        }

        //判断会议室是否有正在预约的记录
        int orderingNum = meetingOrderService.countMeetingRoomExistsOrder(roomId);
        if (orderingNum > 0){
            return Msg.error("会议室有正在进行的预约，无法删除");
        }

        //记录会议室的图片路径
        String path = room.getPath();
        //删除会议室
        try {
            meetingRoomService.deleteRoom(roomId);
            if (!path.equals("meetingroom-default.jpg")){
                //删除原来的文件,如果是默认图片则不删除
                File oldFile = new File(systemRootPath + path);
                if (oldFile.exists()){
                    boolean deleteResult = false;
                    int count = 0;
                    while (!deleteResult && count < 10){
                        System.gc();
                        deleteResult = oldFile.delete();
                        count++;
                    }
                }
            }

            return Msg.success(null);

        } catch (Exception e){
            return Msg.error("删除会议室失败");
        }
    }
}
