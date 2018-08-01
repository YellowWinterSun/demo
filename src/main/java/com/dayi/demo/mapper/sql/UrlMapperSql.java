package com.dayi.demo.mapper.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限表SQL（非逆向工程）
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/10
 */
public interface UrlMapperSql {

    /**
     * 查询某用户所有可用的Url权限
     * @param userId
     * @return
     */
    @Select("select DISTINCT a.url as url\n" +
            "from system_url a, role_to_url b\n" +
            "where status='NORMAL' and a.id=b.url_id and b.role_id in (\n" +
            "	select id\n" +
            "	from role a\n" +
            "	where status='NORMAL' and EXISTS(\n" +
            "		select *\n" +
            "		from user_to_role b\n" +
            "		where b.role_id=a.id and user_id=#{userId}\n" +
            "	)\n" +
            ")")
    List<String> listUserEnableUrl(@Param("userId") String userId);
}
