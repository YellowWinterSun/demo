package com.dayi.demo.dao;

import com.dayi.demo.mapper.MeetingRoomOrderMapper;
import com.dayi.demo.mapper.sql.MeetingOrderMapperSql;
import com.dayi.demo.model.MeetingRoomOrder;
import com.dayi.demo.model.MeetingRoomOrderExample;
import com.dayi.demo.model.modelEnum.MeetingOrderEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.CredentialException;
import java.util.Date;
import java.util.List;

/**
 * 会议室预约Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Repository
public class MeetingOrderDao {
    @Autowired
    private MeetingRoomOrderMapper mapper;
    @Autowired
    private MeetingOrderMapperSql mapperSql;

    /**
     * 查询会议室预约记录(默认按起始时间升序）
     * @param roomId 会议室id
     * @param status 会议室预约状态
     * @return
     */
    public List<MeetingRoomOrder> list(String roomId, String status){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(roomId)){
            c.andRoomIdEqualTo(roomId);
        }
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        example.setOrderByClause("starttime asc");
        return mapper.selectByExample(example);
    }

    /**
     * 获取某个用户的会议室预约情况（可用且默认按会议开始时间升序排序)
     * @param userNo 用户工号
     * @return
     */
    public List<MeetingRoomOrder> listUserOrder(String userNo){
        return mapperSql.listUserOrder(userNo);
    }

    /**
     * 有条件的获取会议预约记录（分页）
     * @param example 条件筛选类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    public List<MeetingRoomOrder> listRoomOrderLimit(MeetingRoomOrderExample example, Integer limitStart, Integer limitEnd){
        return mapper.listRoomOrderLimit(example, limitStart, limitEnd);
    }

    /**
     * 查询会议开始时间符合 [leftTime,rightTime) 的所有预约进行中的会议
     * @param leftTime
     * @param rightTime
     * @return
     */
    public List<MeetingRoomOrder> listRoomOrderBetweenTime(Date leftTime, Date rightTime){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());
        c.andStarttimeGreaterThanOrEqualTo(leftTime);
        c.andStarttimeLessThan(rightTime);

        return mapper.selectByExample(example);
    }

    /**
     * 修改会议记录的会议状态
     * @param orderId 要修改的会议预约id
     * @param status 期望的状态
     * @return
     */
    public int updateOrderStatus(String orderId, String status){
        MeetingRoomOrder updateOrder = new MeetingRoomOrder();
        updateOrder.setId(orderId);
        updateOrder.setStatus(status);

        return mapper.updateByPrimaryKeySelective(updateOrder);
    }

    /**
     * 统计符合条件的记录数量
     * @param example 条件类
     * @return
     */
    public int countByExample(MeetingRoomOrderExample example){
        return mapper.countByExample(example);
    }

    /**
     * 根据id，获取会议预约信息
     * @param id
     * @return
     */
    public MeetingRoomOrder getById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据会议室id，删除与之相关的会议室预约记录
     * @param roomId
     * @return
     */
    public int deleteByRoomId(String roomId){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andRoomIdEqualTo(roomId);

        return mapper.deleteByExample(example);
    }

    /**
     * 根据会议室预约状态，删除记录
     * @param status 要删除的会议室状态
     * @return
     */
    public int deleteByStatus(String status){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(status);

        return mapper.deleteByExample(example);
    }

    /**
     * 将所有已经过期的NORMAL会议预约状态设置为OVERDUE
     * @param nowDate 现在的时间
     * @return
     */
    public int updateStatusToOverdueByEndtime(Date nowDate){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());
        c.andEndtimeLessThanOrEqualTo(nowDate);

        MeetingRoomOrder record = new MeetingRoomOrder();
        record.setStatus(MeetingOrderEnum.OVERDUE.getValue());

        return mapper.updateByExampleSelective(record, example);
    }

    /**
     * 新增一个会议室预约记录
     * @param roomOrder
     * @return
     */
    public int addMeetingOrder(MeetingRoomOrder roomOrder){
        return mapper.insertSelective(roomOrder);
    }

    /**
     * 验证预约时间冲突的第一种情况（要预约的时间区间，冲突了已预约时间的起始时间点）
     * @param roomId 会议室ID
     * @param startTime 预约起始时间
     * @param endTime 预约结束时间
     * @return true无冲突，false有冲突
     */
    public boolean validOrderStartTime(String roomId, Date startTime, Date endTime){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andRoomIdEqualTo(roomId);
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());

        c.andStarttimeGreaterThan(startTime);
        c.andStarttimeLessThan(endTime);

        int num = mapper.countByExample(example);
        return (num > 0) ? false : true;
    }

    /**
     * 验证预约时间冲突的第二种情况（要预约的时间区间，在已预约的时间区间内）
     * @param roomId 会议室ID
     * @param startTime 预约起始时间
     * @param endTime 预约结束时间
     * @return true无冲突，false有冲突
     */
    public boolean validOrderBetweenTime(String roomId, Date startTime, Date endTime){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andRoomIdEqualTo(roomId);
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());

        c.andStarttimeLessThanOrEqualTo(startTime);
        c.andEndtimeGreaterThanOrEqualTo(endTime);

        int num = mapper.countByExample(example);
        return (num > 0) ? false : true;
    }

    /**
     * 验证预约时间冲突的第三种情况（要预约的时间区间，冲突了已预约时间的结束时间点）
     * @param roomId 会议室ID
     * @param startTime 预约起始时间
     * @param endTime 预约结束时间
     * @return true无冲突，false有冲突
     */
    public boolean validOrderEndTime(String roomId, Date startTime, Date endTime){
        MeetingRoomOrderExample example = new MeetingRoomOrderExample();
        MeetingRoomOrderExample.Criteria c = example.createCriteria();
        c.andRoomIdEqualTo(roomId);
        c.andStatusEqualTo(MeetingOrderEnum.NORMAL.getValue());

        c.andEndtimeGreaterThan(startTime);
        c.andEndtimeLessThan(endTime);

        int num = mapper.countByExample(example);
        return (num > 0) ? false : true;
    }
}
