package com.dayi.demo.dao;

import com.dayi.demo.mapper.MeetingRoomMapper;
import com.dayi.demo.model.MeetingRoom;
import com.dayi.demo.model.MeetingRoomExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会议室表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Repository
public class MeetingRoomDao {
    @Autowired
    private MeetingRoomMapper mapper;

    /**
     * 根据条件，查询会议室记录（默认按名字排序）
     * @param remarkLike 会议室描述（模糊搜索）
     * @param nameLike 会议室名称（模糊搜索）
     * @param needSize 需要支持的容量数
     * @return
     */
    public List<MeetingRoom> list(String remarkLike, String nameLike, Integer needSize, String status){
        MeetingRoomExample example = new MeetingRoomExample();
        MeetingRoomExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(remarkLike)){
            c.andRemarkLike("%" + remarkLike + "%");
        }
        if (StringUtils.isNotBlank(nameLike)){
            c.andNameLike("%" + nameLike + "%");
        }
        if (null != needSize){
            c.andSizeGreaterThanOrEqualTo(needSize.shortValue());
        }
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        example.setOrderByClause("name asc");
        return mapper.selectByExample(example);
    }

    /**
     * 根据条件，可分页的查询会议室信息
     * @param example 条件筛选类
     * @param limitStart 分页起始下标
     * @param limitEnd 记录数
     * @return
     */
    public List<MeetingRoom> listLimit(MeetingRoomExample example, Integer limitStart, Integer limitEnd){
        return mapper.listRoomLimit(example, limitStart, limitEnd);
    }

    /**
     * 根据条件，统计匹配的记录数
     * @param example 条件筛选类
     * @return
     */
    public int countByExample(MeetingRoomExample example){
        return mapper.countByExample(example);
    }

    /**
     * 获取会议室信息
     * @param id
     * @return
     */
    public MeetingRoom getById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 新增一个会议室
     * @param roomSelective
     * @return
     */
    public int addMeetingRoom(MeetingRoom roomSelective){
        return mapper.insertSelective(roomSelective);
    }

    /**
     * 根据id，更新会议室信息
     * @param roomSelective
     * @return
     */
    public int updateRoom(MeetingRoom roomSelective){
        return mapper.updateByPrimaryKeySelective(roomSelective);
    }

    /**
     * 根据id，删除会议室
     * @param roomId
     * @return
     */
    public int deleteRoom(String roomId){
        return mapper.deleteByPrimaryKey(roomId);
    }
}
