package com.dayi.demo.mapper;

import com.dayi.demo.model.Job;
import com.dayi.demo.model.JobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {
    int countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(String id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    /* 非逆向工程 */

    /**
     * 根据特定条件搜索job表（全部条件置为null，则默认全表返回）
     * @param name 岗位名称（模糊搜索)
     * @param departmentName 部门名称（模糊搜索)
     * @param limitStart 分页起始下标
     * @param limitEnd 结果记录数
     * @param orderByClause 排序规则
     * @return
     */
    List<Job> listJob(@Param("name") String name, @Param("departmentName") String departmentName,
                      @Param("limitStart") Integer limitStart,
                      @Param("limitEnd") Integer limitEnd, @Param("orderByClause") String orderByClause);
}