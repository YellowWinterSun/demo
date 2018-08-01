package com.dayi.demo.mapper;

import com.dayi.demo.model.TemplateToItem;
import com.dayi.demo.model.TemplateToItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateToItemMapper {
    int countByExample(TemplateToItemExample example);

    int deleteByExample(TemplateToItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(TemplateToItem record);

    int insertSelective(TemplateToItem record);

    List<TemplateToItem> selectByExample(TemplateToItemExample example);

    TemplateToItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TemplateToItem record, @Param("example") TemplateToItemExample example);

    int updateByExample(@Param("record") TemplateToItem record, @Param("example") TemplateToItemExample example);

    int updateByPrimaryKeySelective(TemplateToItem record);

    int updateByPrimaryKey(TemplateToItem record);
}