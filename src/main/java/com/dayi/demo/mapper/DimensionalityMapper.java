package com.dayi.demo.mapper;

import com.dayi.demo.model.Dimensionality;
import com.dayi.demo.model.DimensionalityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DimensionalityMapper {
    int countByExample(DimensionalityExample example);

    int deleteByExample(DimensionalityExample example);

    int deleteByPrimaryKey(String id);

    int insert(Dimensionality record);

    int insertSelective(Dimensionality record);

    List<Dimensionality> selectByExample(DimensionalityExample example);

    Dimensionality selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Dimensionality record, @Param("example") DimensionalityExample example);

    int updateByExample(@Param("record") Dimensionality record, @Param("example") DimensionalityExample example);

    int updateByPrimaryKeySelective(Dimensionality record);

    int updateByPrimaryKey(Dimensionality record);
}