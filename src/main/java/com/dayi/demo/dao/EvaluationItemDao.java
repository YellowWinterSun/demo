package com.dayi.demo.dao;

import com.dayi.demo.mapper.EvaluationItemMapper;
import com.dayi.demo.mapper.sql.EvaluationItemMapperSql;
import com.dayi.demo.model.EvaluationItem;
import com.dayi.demo.model.EvaluationItemExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模板 - 考核项目表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Repository
public class EvaluationItemDao {

    @Autowired
    private EvaluationItemMapper evaluationItemMapper;
    @Autowired
    private EvaluationItemMapperSql evaluationItemMapperSql;

    /**
     * 根据特定条件搜索模板考核项目对象（默认按照考核维度排序）
     * @param name 考核项目名(模糊搜索）
     * @param dimensionalityId 考核维度ID
     * @return
     */
    public List<EvaluationItem> listItem(String name, String dimensionalityId){
        EvaluationItemExample example = new EvaluationItemExample();
        EvaluationItemExample.Criteria c = example.createCriteria();
        example.setOrderByClause("dimensionality_id ASC");

        if (StringUtils.isNotBlank(name)){
            c.andNameLike("%" + name + "%");
        }
        if (StringUtils.isNotBlank(dimensionalityId)){
            c.andDimensionalityIdEqualTo(dimensionalityId);
        }

        return evaluationItemMapper.selectByExample(example);
    }

    /***
     * 根据考核项目id获取考核项目信息
     * @param id 考核项目UUID
     * @return
     */
    public EvaluationItem getItemById(String id){
        return evaluationItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据考核项目id，删除考核项目
     * @param id
     * @return
     */
    public int deleteItemById(String id){
        return evaluationItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 有选择的更新考核项目信息
     * @param itemSelective
     * @return
     */
    public int updateItemById(EvaluationItem itemSelective){
        return evaluationItemMapper.updateByPrimaryKeySelective(itemSelective);
    }

    /**
     * 增加一个新的考核项目
     * @param item
     * @return
     */
    public int addItem(EvaluationItem item){
        return evaluationItemMapper.insertSelective(item);
    }

    /**
     * 按照考核模板id获取考核项目信息（按考核维度优先级降序排序)
     * @param id 考核模板id
     * @return
     */
    public List<EvaluationItem> listItem(String id){
        return evaluationItemMapperSql.listItemByTemplateId(id);
    }
}
