package com.dayi.demo.dao;

import com.dayi.demo.mapper.TemplateToItemMapper;
import com.dayi.demo.mapper.sql.TemplateToItemMapperSql;
import com.dayi.demo.model.TemplateToItem;
import com.dayi.demo.model.TemplateToItemExample;
import com.dayi.demo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 绩效考核模板 - 考核项目 - 中间表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Repository
public class TemplateToItemDao {

    @Autowired
    private TemplateToItemMapper templateToItemMapper;
    @Autowired
    private TemplateToItemMapperSql templateToItemMapperSql;

    /**
     * 根据绩效考核模板id获取总权重
     * @param id 绩效考核模板id
     * @return 权重总分
     */
    public int countTemplateWeight(String id){
        return templateToItemMapperSql.countTemplateWeight(id);
    }

    /**
     * 根据绩效考核模板表id，获取中间表对象
     * @param templateId 绩效考核模板id
     * @return
     */
    public List<TemplateToItem> listTemplateToItemByTemplateId(String templateId){
        TemplateToItemExample example = new TemplateToItemExample();
        TemplateToItemExample.Criteria c = example.createCriteria();
        c.andTemplateIdEqualTo(templateId);

        return templateToItemMapper.selectByExample(example);
    }

    /**
     * 根据考核项目id，删除中间表对应记录
     * @param id 模板-考核项目id
     * @return
     */
    public int deleteByItemId(String id){
        TemplateToItemExample example = new TemplateToItemExample();
        TemplateToItemExample.Criteria c = example.createCriteria();
        c.andItemIdEqualTo(id);

        return templateToItemMapper.deleteByExample(example);
    }

    /**
     * 根据绩效考核模板id，删除对应记录
     * @param id 绩效考核模板表id
     * @return
     */
    public int deleteByTemplateId(String id){
        TemplateToItemExample example = new TemplateToItemExample();
        TemplateToItemExample.Criteria c = example.createCriteria();
        c.andTemplateIdEqualTo(id);

        return templateToItemMapper.deleteByExample(example);
    }

    /**
     * 增加中间表记录
     * @param templateId
     * @param itemId
     * @return
     */
    public int add(String templateId, String itemId){
        TemplateToItem record = new TemplateToItem();
        record.setId(UUIDUtil.getUUID());
        record.setTemplateId(templateId);
        record.setItemId(itemId);

        return templateToItemMapper.insertSelective(record);
    }


}
