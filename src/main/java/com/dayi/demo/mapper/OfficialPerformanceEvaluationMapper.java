package com.dayi.demo.mapper;

import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.OfficialPerformanceEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfficialPerformanceEvaluationMapper {
    int countByExample(OfficialPerformanceEvaluationExample example);

    int deleteByExample(OfficialPerformanceEvaluationExample example);

    int deleteByPrimaryKey(String id);

    int insert(OfficialPerformanceEvaluation record);

    int insertSelective(OfficialPerformanceEvaluation record);

    List<OfficialPerformanceEvaluation> selectByExample(OfficialPerformanceEvaluationExample example);

    OfficialPerformanceEvaluation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OfficialPerformanceEvaluation record, @Param("example") OfficialPerformanceEvaluationExample example);

    int updateByExample(@Param("record") OfficialPerformanceEvaluation record, @Param("example") OfficialPerformanceEvaluationExample example);

    int updateByPrimaryKeySelective(OfficialPerformanceEvaluation record);

    int updateByPrimaryKey(OfficialPerformanceEvaluation record);

    //非逆向工程

    /**
     * 根据条件，分页的获取信息
     * @param example 筛选条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    List<OfficialPerformanceEvaluation> listOfficialEvaluation(@Param("example") OfficialPerformanceEvaluationExample example,
                                                               @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
}