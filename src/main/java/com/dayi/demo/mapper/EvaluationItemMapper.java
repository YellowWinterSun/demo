package com.dayi.demo.mapper;

import com.dayi.demo.model.EvaluationItem;
import com.dayi.demo.model.EvaluationItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluationItemMapper {
    int countByExample(EvaluationItemExample example);

    int deleteByExample(EvaluationItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(EvaluationItem record);

    int insertSelective(EvaluationItem record);

    List<EvaluationItem> selectByExample(EvaluationItemExample example);

    EvaluationItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EvaluationItem record, @Param("example") EvaluationItemExample example);

    int updateByExample(@Param("record") EvaluationItem record, @Param("example") EvaluationItemExample example);

    int updateByPrimaryKeySelective(EvaluationItem record);

    int updateByPrimaryKey(EvaluationItem record);
}