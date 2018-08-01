package com.dayi.demo.mapper;

import com.dayi.demo.model.RoleToUrl;
import com.dayi.demo.model.RoleToUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleToUrlMapper {
    int countByExample(RoleToUrlExample example);

    int deleteByExample(RoleToUrlExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleToUrl record);

    int insertSelective(RoleToUrl record);

    List<RoleToUrl> selectByExample(RoleToUrlExample example);

    RoleToUrl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleToUrl record, @Param("example") RoleToUrlExample example);

    int updateByExample(@Param("record") RoleToUrl record, @Param("example") RoleToUrlExample example);

    int updateByPrimaryKeySelective(RoleToUrl record);

    int updateByPrimaryKey(RoleToUrl record);
}