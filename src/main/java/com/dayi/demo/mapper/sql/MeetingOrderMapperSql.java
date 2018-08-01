package com.dayi.demo.mapper.sql;

import com.dayi.demo.model.MeetingRoomOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会议室预约表SQL（非逆向工程）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/3
 */
public interface MeetingOrderMapperSql {

    /**
     * 获取某位用户可用的会议预约情况（默认按会议开始时间升序排序）
     * @param userNo
     * @return
     */
    @Select("select id, createtime, updatetime, starttime, endtime, order_user_no, join_users_no, room_id, status\n" +
            "from \n" +
            "(select id, createtime, updatetime, starttime, endtime, order_user_no, join_users_no, room_id, status \n" +
            "from meeting_room_order \n" +
            "where status='NORMAL') a\n" +
            "where order_user_no=#{no} \n" +
            "or join_users_no like CONCAT(#{no},',','%') \n" +
            "or join_users_no like CONCAT('%,',#{no},',%')\n" +
            "or join_users_no like CONCAT('%,',#{no})\n" +
            "or join_users_no = #{no}\n" +
            "order by starttime ASC")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="starttime", property="starttime"),
            @Result(column="endtime", property="endtime"),
            @Result(column="order_user_no", property="orderUserNo"),
            @Result(column="createtime", property="createtime"),
            @Result(column="updatetime", property="updatetime"),
            @Result(column="join_users_no", property="joinUsersNo"),
            @Result(column="room_id", property="roomId"),
            @Result(column="status", property="status")
    })
    List<MeetingRoomOrder> listUserOrder(@Param("no") String userNo);

}
