package com.dayi.demo.mapper;

import com.dayi.demo.model.PerformanceEvaluationTemplate;
import com.dayi.demo.model.PerformanceEvaluationTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerformanceEvaluationTemplateMapper {
    int countByExample(PerformanceEvaluationTemplateExample example);

    int deleteByExample(PerformanceEvaluationTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(PerformanceEvaluationTemplate record);

    int insertSelective(PerformanceEvaluationTemplate record);

    List<PerformanceEvaluationTemplate> selectByExample(PerformanceEvaluationTemplateExample example);

    PerformanceEvaluationTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PerformanceEvaluationTemplate record, @Param("example") PerformanceEvaluationTemplateExample example);

    int updateByExample(@Param("record") PerformanceEvaluationTemplate record, @Param("example") PerformanceEvaluationTemplateExample example);

    int updateByPrimaryKeySelective(PerformanceEvaluationTemplate record);

    int updateByPrimaryKey(PerformanceEvaluationTemplate record);

    /* 非逆向工程 */

    /**
     * 根据特定条件模糊搜索
     * @param name 模板表名称（模糊搜索）
     * @param jobId 岗位ID（非模糊）
     * @param limitStart 分页起始
     * @param limitEnd 分页记录数
     * @param orderByClause 排序规则
     * @return
     */
    List<PerformanceEvaluationTemplate> listTemplate(@Param("name") String name, @Param("jobId") String jobId,
                                                     @Param("limitStart") Integer limitStart,
                                                     @Param("limitEnd") Integer limitEnd, @Param("orderByClause") String orderByClause);
}