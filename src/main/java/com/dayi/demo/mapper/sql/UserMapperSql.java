package com.dayi.demo.mapper.sql;

import com.dayi.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * user表的SQL语句（非逆向工程生成）
 * 用于编写特别的SQL
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/16
 */
public interface UserMapperSql {

    /**
     * 更新用户的直属上司字段（将直属上司设为NULL）
     * @param deleteNo 即将被设NULL的，直属上司NO字段条件
     * @return
     */
    @Update("update user set direct_supervisor_no = NULL where direct_supervisor_no = #{no}")
    int updateUserDirectSupervisorNoSetNull(@Param("no") String deleteNo);

    /**
     * 根据用户id，将直属上司设为NULL
     * @param id uuid
     * @return
     */
    @Update("update user set direct_supervisor_no = NULL where id = #{id}")
    int updateUserDirectSupervisorNoSetNullById(@Param("id") String id);

    /**
     * （全表更新）根据匹配的岗位id，更新其岗位名称
     * @param jobId 需要匹配的岗位id
     * @param newJobName 新的岗位名称
     * @return
     */
    @Update("update user set job_name=#{newJobName} where job_id=#{jobId}")
    int updateUserJobNameByJobId(@Param("jobId") String jobId, @Param("newJobName") String newJobName);

    /**
     * (全表更新）根据匹配的岗位id，更新其部门名称
     * @param jobId 需要匹配的岗位id
     * @param newDepartmentName 新的部门名称
     * @return
     */
    @Update("update user set department_name=#{newDepartmentName} where job_id=#{jobId}")
    int updateUserDepartmentNameByJobId(@Param("jobId") String jobId, @Param("newDepartmentName") String newDepartmentName);

    /**
     * （全表更新）根据旧的部门名称，修改未新的部门名称
     * @param oldName 旧部门名称
     * @param newName 新部门名称
     * @return
     */
    @Update("update user set department_name=#{newName} where department_name=#{oldName}")
    int updateUserDepartmentNameByDepartmentName(@Param("oldName") String oldName, @Param("newName") String newName);

    /**
     * 判断bossNo是否为userNo这个用户的部门负责人
     * @param bossNo 待判断的部门负责人工号
     * @param userNo 用户工号
     * @return
     */
    @Select("select count(*)\n" +
            "from job a, department b\n" +
            "where a.department_id=b.id and b.majordomo=#{bossNo} and a.id=(select job_id from user where no=#{userNo})")
    int validBossIsUserDepartmentMajordomo(@Param("bossNo") String bossNo, @Param("userNo") String userNo);
}
