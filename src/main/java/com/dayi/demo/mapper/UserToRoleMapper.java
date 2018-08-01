package com.dayi.demo.mapper;

import com.dayi.demo.model.UserToRole;
import com.dayi.demo.model.UserToRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserToRoleMapper {
    int countByExample(UserToRoleExample example);

    int deleteByExample(UserToRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserToRole record);

    int insertSelective(UserToRole record);

    List<UserToRole> selectByExample(UserToRoleExample example);

    UserToRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserToRole record, @Param("example") UserToRoleExample example);

    int updateByExample(@Param("record") UserToRole record, @Param("example") UserToRoleExample example);

    int updateByPrimaryKeySelective(UserToRole record);

    int updateByPrimaryKey(UserToRole record);
}