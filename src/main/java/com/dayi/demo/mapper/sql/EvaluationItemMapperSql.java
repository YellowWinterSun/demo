package com.dayi.demo.mapper.sql;

import com.dayi.demo.model.EvaluationItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 模板 - 考核项目SQL（非逆向工程）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/26
 */
public interface EvaluationItemMapperSql {

    /**
     * 根据考核模板id，获取其考核项目信息（按照考核维度降序排序)
     * @return
     */
    @Select("select b.id as id, b.name as name, b.weight as weight, b.content as content, b.createtime as createtime" +
            ", b.updatetime as updatetime, b.dimensionality_id as dimensionality_id \n" +
            "from template_to_item a, evaluation_item b, dimensionality c\n" +
            "where a.item_id=b.id and b.dimensionality_id=c.id and a.template_id=#{id}\n" +
            "order by c.level desc")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="name", property="name"),
            @Result(column="weight", property="weight"),
            @Result(column="content", property="content"),
            @Result(column="createtime", property="createtime"),
            @Result(column="updatetime", property="updatetime"),
            @Result(column="dimensionality_id", property="dimensionalityId")
    })
    List<EvaluationItem> listItemByTemplateId(@Param("id") String templateId);
}
