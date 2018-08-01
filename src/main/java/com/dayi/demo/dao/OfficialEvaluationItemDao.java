package com.dayi.demo.dao;

import com.dayi.demo.mapper.OfficialEvaluationItemMapper;
import com.dayi.demo.mapper.sql.OfficialEvaluationItemMapperSql;
import com.dayi.demo.model.OfficialEvaluationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 正表绩效考核项目 Dao
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
@Repository
public class OfficialEvaluationItemDao {
    @Autowired
    private OfficialEvaluationItemMapper mapper;
    @Autowired
    private OfficialEvaluationItemMapperSql mapperSql;

    /**
     * 新增一个正表绩效考核项目
     * @param officialEvaluationItemSelective
     * @return
     */
    public int addOfficialItem(OfficialEvaluationItem officialEvaluationItemSelective){
        return mapper.insertSelective(officialEvaluationItemSelective);
    }

    /**
     * 根据绩效考核表id，获取其考核项目信息（按考核维度优先级降序排序）
     * @param officialEvaluationId 考核表id
     * @return
     */
    public List<OfficialEvaluationItem> listOfficialItem(String officialEvaluationId){
        return mapperSql.listItemByOfficialId(officialEvaluationId);
    }

    /**
     * 根据id，获取绩效考核项目信息
     * @param id 绩效考核项目id
     * @return
     */
    public OfficialEvaluationItem getById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id，选择性的更新
     * @param selective
     * @return
     */
    public int updateOfficialItem(OfficialEvaluationItem selective){
        return mapper.updateByPrimaryKeySelective(selective);
    }
}
