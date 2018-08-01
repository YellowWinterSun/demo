package com.dayi.demo.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 部门表的sql语句（非逆向工程代码）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
public interface DepartmentMapperSql {

    /**
     * 根据部门负责人工号，清除所有是该负责人的部门的部门负责人字段
     * @param deleteNo 即将剥夺部门负责人权限的用户工号
     * @return
     */
    @Update("update department set majordomo = NULL where majordomo = #{no}")
    int updateDepartmentMajordomoSetNull(@Param("no") String deleteNo);

    /**
     * （全表更新）根据旧的父级部门id，更新为新的父级部门id
     * @param oldId 旧id
     * @param newId 新id
     * @return
     */
    @Update("update department set parent_id=#{newId} where parent_id=#{oldId}")
    int updateDepartmentParentId(@Param("oldId") String oldId, @Param("newId") String newId);

    /**
     * （全表更新）根据旧的父级部门id，更新为NULL
     * @param oldId 旧id
     * @return
     */
    @Update("update department set parent_id=NULL where parent_id=#{oldId}")
    int updateDepartmentParentIdSetNull(@Param("oldId") String oldId);
}
