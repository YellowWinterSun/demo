package com.dayi.demo.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 绩效考核正表的SQL（非逆向工程）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/20
 */
public interface OfficialPerformanceEvaluationMapperSql {

    /**
     * 根据考核人的工号，修改考核人的签名（全表修改）
     * @return
     */
    @Update("update official_performance_evaluation set user_name=#{newName} where user_no=#{no}")
    int updateUserNameByNo(@Param("newName") String newUserName, @Param("no") String userNo);

    /**
     * 根据直属上司的工号，修改直属上司的签名（全表修改）
     * @return
     */
    @Update("update official_performance_evaluation set boss1_name=#{newName} where boss1_no=#{no}")
    int updateBoss1NameByNo(@Param("newName") String newUserName, @Param("no") String userNo);

    /**
     * 根据部门负责人的工号，修改部门负责人的签名（全表修改）
     * @return
     */
    @Update("update official_performance_evaluation set boss2_name=#{newName} where boss2_no=#{no}")
    int updateBoss2NameByNo(@Param("newName") String newUserName, @Param("no") String userNo);

    /**
     * 根据人事负责人的工号，修改其签名（全表修改）
     * @return
     */
    @Update("update official_performance_evaluation set hr_name=#{newName} where hr_no=#{no}")
    int updateHrNameByNo(@Param("newName") String newUserName, @Param("no") String userNo);

    /**
     * 传入绩效考核表的id和直属上司的工号。判断这个绩效考核表的直属上司是否为userNo这个用户
     * @param officialEvaluationId 绩效考核表id
     * @param bossNo 要验证的用户工号
     * @return 返回1则表示是直属上司，返回0则不是。
     */
    @Select("select count(*)\n" +
            "from `user`\n" +
            "where direct_supervisor_no=#{bossNo} " +
            "and no=(select user_no from official_performance_evaluation where id=#{id} )")
    int countOfficialEvaluationByIdAndBossNo(@Param("id") String officialEvaluationId, @Param("bossNo") String bossNo);
}
