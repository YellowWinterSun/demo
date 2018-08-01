package com.dayi.demo.mapper;

import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingRoomMapper {
    int countByExample(MeetingRoomExample example);

    int deleteByExample(MeetingRoomExample example);

    int deleteByPrimaryKey(String id);

    int insert(MeetingRoom record);

    int insertSelective(MeetingRoom record);

    List<MeetingRoom> selectByExample(MeetingRoomExample example);

    MeetingRoom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MeetingRoom record, @Param("example") MeetingRoomExample example);

    int updateByExample(@Param("record") MeetingRoom record, @Param("example") MeetingRoomExample example);

    int updateByPrimaryKeySelective(MeetingRoom record);

    int updateByPrimaryKey(MeetingRoom record);

    //非逆向工程
    /**
     * 根据条件，分页的获取信息
     * @param example 筛选条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    List<MeetingRoom> listRoomLimit(@Param("example") MeetingRoomExample example,
                                    @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
}