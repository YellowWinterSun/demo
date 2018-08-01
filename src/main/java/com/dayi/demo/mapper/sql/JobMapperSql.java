package com.dayi.demo.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Job表的数据库操作
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/20
 */
public interface JobMapperSql {

    /**
     * （全表修改）根据部门id，修改部门名称
     * @param id 要匹配的部门id
     * @param newName 要修改的新部门名称
     * @return
     */
    @Update("update job set department_name=#{newName} where department_id=#{id}")
    int updateJobDepartmentNameByDepartmentId(@Param("id") String id, @Param("newName") String newName);
}
