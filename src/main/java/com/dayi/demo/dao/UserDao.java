package com.dayi.demo.dao;

import com.dayi.demo.mapper.UserMapper;
import com.dayi.demo.mapper.sql.UserMapperSql;
import com.dayi.demo.model.Job;
import com.dayi.demo.model.JobExample;
import com.dayi.demo.model.User;
import com.dayi.demo.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User表的Dao层
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapperSql userMapperSql;

    /**
     * 根据手机号码获取user信息
     * @param phone 手机号码
     * @return 如果没有则返回null
     */
    public User getUserByPhone(String phone){
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();

        c.andPhoneEqualTo(phone);

        List<User> list = userMapper.selectByExample(example);
        if(list.size() > 0)
            return list.get(0);

        return null;
    }

    /**
     * 根据工号获取user信息
     * @param no 工号
     * @return 没有返回NULL
     */
    public User getUserByNo(String no){
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();

        c.andNoEqualTo(no);

        List<User> list = userMapper.selectByExample(example);
        if(list.size() > 0)
            return list.get(0);

        return null;
    }

    /**
     * 根据UUID获取用户信息
     * @param id UUID
     * @return
     */
    public User getUserById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据UserExample指定的条件，统计数据库记录数量
     * @param example UserExample
     * @return 符合条件的记录数
     */
    public int countByExample(UserExample example){
        return userMapper.countByExample(example);
    }

    /**
     * 根据特定条件模糊搜索
     * @param name          姓名（模糊搜索）
     * @param phone         电话号码（模糊搜索）
     * @param no            工号（模糊搜索）
     * @param limitStart    分页起始下标
     * @param limitEnd      记录个数
     * @param orderByClause 排序规则
     * @return 所有条件为null，默认返回全部对象
     */
    public List<User> listUser(String name, String phone, String no,
                                Integer limitStart, Integer limitEnd, String orderByClause){
        return userMapper.listUser(name, phone, no, limitStart, limitEnd, orderByClause);
    }

    /**
     * 根据条件，搜索用户信息
     * @param listIds 用户id范围
     * @param nameLike 用户名称(模糊搜索）
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    public List<User> listUserLimitExample(List<String> listIds, String nameLike,
                                           Integer limitStart, Integer limitEnd, String order, String sort){
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        if (null != listIds && !listIds.isEmpty()){
            c.andIdIn(listIds);
        }
        if (StringUtils.isNotBlank(nameLike)){
            c.andNameLike("%" + nameLike + "%");
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        return userMapper.listUserLimitExample(example, limitStart, limitEnd);
    }

    /**
     * 根据直属上司工号获取所有用户
     * @param directSupervisorNo 直属上司工号
     * @return
     */
    public List<User> listUserByDirectSupervisorNo(String directSupervisorNo){
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andDirectSupervisorNoEqualTo(directSupervisorNo);
        return userMapper.selectByExample(example);
    }

    /**
     * 根据部门名称集合，搜索所有用户
     * @param departmentNames 部门名称集合
     * @return
     */
    public List<User> listUserByDepartmentNames(List<String> departmentNames){
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andDepartmentNameIn(departmentNames);
        return userMapper.selectByExample(example);
    }

    /**
     * 获取不在所给范围的其他所有用户信息（如果传入为空，则返回null）
     * @param userIds 要排除的用户id集
     * @return
     */
    public List<User> listUserNotInIds(List<String> userIds){
        if (null == userIds || userIds.isEmpty()){
            return null;
        }

        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andIdNotIn(userIds);

        return userMapper.selectByExample(example);
    }

    /**
     * 新增一个user
     * @param newUser 封装好的User ORM对象
     * @return 更新数据库的记录数
     */
    public int addUser(User newUser){
        return userMapper.insertSelective(newUser);
    }

    /**
     * 根据条件删除记录（不需要提供的条件置NULL）
     * @param id UUID
     * @param phone 手机号码
     * @param no 工号
     * @return 更新数据库的记录数
     */
    public int deleteUser(String id, String phone, String no){
        //如果传入的是id，直接根据id删除记录
        if(StringUtils.isNotBlank(id)){
            return userMapper.deleteByPrimaryKey(id);
        }

        //其他情况，封装Example
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();

        if(StringUtils.isNotBlank(phone)){
            c.andPhoneEqualTo(phone);
        }
        if(StringUtils.isNotBlank(no)){
            c.andNoEqualTo(no);
        }

        return userMapper.deleteByExample(example);
    }

    /**
     * 将所有直属上司工号是deleteNo的用户，改字段set null
     * @param deleteNo 要处理的工号
     */
    public void updateUserDirectSupervisorNoSetNull(String deleteNo){
        userMapperSql.updateUserDirectSupervisorNoSetNull(deleteNo);
    }

    /**
     * 选择性的更新用户信息（根据UUID）
     * @param userSelective
     * @return
     */
    public int updateUser(User userSelective){
        return userMapper.updateByPrimaryKeySelective(userSelective);
    }

    /**
     * 根据UUID，将当前用户的直属上司设置为NULL
     * @param id UUID
     * @return
     */
    public int updateUserDirectSupervisorNoSetNullById(String id){
        return userMapperSql.updateUserDirectSupervisorNoSetNullById(id);
    }

    /**
     * （全表更新）根据匹配的岗位ID，更新新的岗位名称
     * @param jobId 要匹配的岗位ID
     * @param newJobName 要更新的岗位名称
     * @return
     */
    public int updateUserJobNameByJobId(String jobId, String newJobName){
        return userMapperSql.updateUserJobNameByJobId(jobId, newJobName);
    }

    /**
     * (全表更新）根据匹配的岗位id，更新新的部门名称
     * @param jobId 要匹配的岗位ID
     * @param newDepartmentName 要更新的部门名称
     * @return
     */
    public int updateUserDepartmentNameByJobId(String jobId, String newDepartmentName){
        return userMapperSql.updateUserDepartmentNameByJobId(jobId, newDepartmentName);
    }

    /**
     * （全表更新）根据旧的部门名称，修改未新的部门名称
     * @param oldName 旧部门名称
     * @param newName 新部门名称
     * @return
     */
    public int updateUserDepartmentNameByDepartmentName(String oldName, String newName){
        return userMapperSql.updateUserDepartmentNameByDepartmentName(oldName, newName);
    }

    /**
     * 判断bossNo是否为userNo的部门负责人
     * @param bossNo 待验证的部门负责人工号
     * @param userNo 用户工号
     * @return
     */
    public boolean validBossIsUserDepartmentMajordomo(String bossNo, String userNo){
        int num = userMapperSql.validBossIsUserDepartmentMajordomo(bossNo, userNo);
        return (num > 0) ? true : false;
    }
}
