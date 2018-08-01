package com.dayi.demo.mapper;

import com.dayi.demo.model.User;
import com.dayi.demo.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /* 非逆向工程 */

    /**
     * 根据特定条件搜索user表（全部条件为null，则默认返回全表）
     * @param name 姓名（模糊搜索）
     * @param phone 手机号码（模糊搜索）
     * @param no 工号（模糊搜索）
     * @param limitStart 分页起始下标
     * @param limitEnd 结果记录个数
     * @param orderByClause 排序规则（如 '字段名 ASC'）
     * @return 符合条件的user集合
     */
    List<User> listUser(@Param("name") String name, @Param("phone") String phone,
                        @Param("no") String no, @Param("limitStart") Integer limitStart,
                        @Param("limitEnd") Integer limitEnd, @Param("orderByClause") String orderByClause);

    /**
     * 根据条件，可分页的搜索信息
     * @param example 条件筛选类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    List<User> listUserLimitExample(@Param("example") UserExample example, @Param("limitStart") Integer limitStart,
                                    @Param("limitEnd") Integer limitEnd);
}