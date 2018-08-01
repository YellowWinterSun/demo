package com.dayi.demo.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 绩效考核模板 - 考核项目 - 中间表的数据库Mapper接口
 * （非逆向工程）
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
public interface TemplateToItemMapperSql {

    /**
     * 统计某个绩效考核模板的权重总分
     * @param id 绩效考核模板id
     * @return 权重总分
     */
    @Select("select sum(b.weight)\n" +
            "from template_to_item a, evaluation_item b\n" +
            "where a.item_id=b.id and a.template_id=#{id}")
    int countTemplateWeight(@Param("id") String id);
}
