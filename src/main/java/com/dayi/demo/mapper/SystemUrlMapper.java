package com.dayi.demo.mapper;

import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomExample;
import com.dayi.demo.model.SystemUrl;
import com.dayi.demo.model.SystemUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemUrlMapper {
    int countByExample(SystemUrlExample example);

    int deleteByExample(SystemUrlExample example);

    int deleteByPrimaryKey(String id);

    int insert(SystemUrl record);

    int insertSelective(SystemUrl record);

    List<SystemUrl> selectByExample(SystemUrlExample example);

    SystemUrl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SystemUrl record, @Param("example") SystemUrlExample example);

    int updateByExample(@Param("record") SystemUrl record, @Param("example") SystemUrlExample example);

    int updateByPrimaryKeySelective(SystemUrl record);

    int updateByPrimaryKey(SystemUrl record);

    //非逆向工程
    /**
     * 根据条件，分页的获取信息
     * @param example 筛选条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    List<SystemUrl> listUrlLimit(@Param("example") SystemUrlExample example,
                                    @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
}