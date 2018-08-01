package com.dayi.demo.mapper;

import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.MeetingRoomOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingRoomOrderMapper {
    int countByExample(MeetingRoomOrderExample example);

    int deleteByExample(MeetingRoomOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(MeetingRoomOrder record);

    int insertSelective(MeetingRoomOrder record);

    List<MeetingRoomOrder> selectByExample(MeetingRoomOrderExample example);

    MeetingRoomOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MeetingRoomOrder record, @Param("example") MeetingRoomOrderExample example);

    int updateByExample(@Param("record") MeetingRoomOrder record, @Param("example") MeetingRoomOrderExample example);

    int updateByPrimaryKeySelective(MeetingRoomOrder record);

    int updateByPrimaryKey(MeetingRoomOrder record);

    //非逆向工程
    /**
     * 根据条件，分页的获取信息
     * @param example 筛选条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    List<MeetingRoomOrder> listRoomOrderLimit(@Param("example") MeetingRoomOrderExample example,
                                              @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
}