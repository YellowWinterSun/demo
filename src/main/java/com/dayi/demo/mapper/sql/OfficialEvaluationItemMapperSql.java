package com.dayi.demo.mapper.sql;

import com.dayi.demo.model.OfficialEvaluationItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 正表考核项目SQL（非逆向工程）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
public interface OfficialEvaluationItemMapperSql {

    /**
     * 根据绩效考核正表id，获取绩效考核项目（按照考核维度优先级降序排序)
     * @param officialEvaluationId
     * @return
     */
    @Select("select id,createtime,updatetime,official_id,dimensionality_name,dimensionality_level,name,weight,content,self_content,self_grade,boss_grade\n" +
            "from official_evaluation_item\n" +
            "where official_id=#{id}\n" +
            "order by dimensionality_level desc, dimensionality_name")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column="weight", property="weight"),
            @Result(column="content", property="content"),
            @Result(column="createtime", property="createtime"),
            @Result(column="updatetime", property="updatetime"),
            @Result(column="official_id", property="officialId"),
            @Result(column="dimensionality_name", property="dimensionalityName"),
            @Result(column="dimensionality_level", property="dimensionalityLevel"),
            @Result(column="self_content", property="selfContent"),
            @Result(column="self_grade", property="selfGrade"),
            @Result(column="boss_grade", property="bossGrade"),
    })
    List<OfficialEvaluationItem> listItemByOfficialId(@Param("id") String officialEvaluationId);
}
