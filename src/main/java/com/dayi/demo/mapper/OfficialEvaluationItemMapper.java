package com.dayi.demo.mapper;

import com.dayi.demo.model.OfficialEvaluationItem;
import com.dayi.demo.model.OfficialEvaluationItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfficialEvaluationItemMapper {
    int countByExample(OfficialEvaluationItemExample example);

    int deleteByExample(OfficialEvaluationItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(OfficialEvaluationItem record);

    int insertSelective(OfficialEvaluationItem record);

    List<OfficialEvaluationItem> selectByExample(OfficialEvaluationItemExample example);

    OfficialEvaluationItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OfficialEvaluationItem record, @Param("example") OfficialEvaluationItemExample example);

    int updateByExample(@Param("record") OfficialEvaluationItem record, @Param("example") OfficialEvaluationItemExample example);

    int updateByPrimaryKeySelective(OfficialEvaluationItem record);

    int updateByPrimaryKey(OfficialEvaluationItem record);
}