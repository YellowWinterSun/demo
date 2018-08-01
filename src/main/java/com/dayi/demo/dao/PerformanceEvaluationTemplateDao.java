package com.dayi.demo.dao;

import com.dayi.demo.mapper.PerformanceEvaluationTemplateMapper;
import com.dayi.demo.model.PerformanceEvaluationTemplate;
import com.dayi.demo.model.PerformanceEvaluationTemplateExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 绩效考核模板表的Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Repository
public class PerformanceEvaluationTemplateDao {

    @Autowired
    private PerformanceEvaluationTemplateMapper templateMapper;

    /**
     * 根据特定条件模糊搜索
     * @param name 模板表名称（模糊搜索）
     * @param jobId 岗位ID（非模糊）
     * @param limitStart 分页起始
     * @param limitEnd 分页记录数
     * @param orderByClause 排序规则
     * @return
     */
    public List<PerformanceEvaluationTemplate> listTemplate(String name, String jobId,
                                                              Integer limitStart, Integer limitEnd, String orderByClause){
        return templateMapper.listTemplate(name, jobId, limitStart, limitEnd, orderByClause);
    }

    /**
     * 根据特定条件，统计记录总数
     * @param name 模板表名称（模糊搜索）
     * @param jobId 岗位ID（非模糊）
     * @return
     */
    public int countTemplate(String name, String jobId){
        PerformanceEvaluationTemplateExample example = new PerformanceEvaluationTemplateExample();
        PerformanceEvaluationTemplateExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            c.andNameLike("%" + name + "%");
        }
        if (StringUtils.isNotBlank(jobId)){
            c.andJobIdEqualTo(jobId);
        }

        return templateMapper.countByExample(example);
    }

    /**
     * 增加一个新的绩效考核模板
     * @param templateSelective
     * @return
     */
    public int addTemplate(PerformanceEvaluationTemplate templateSelective){
        return templateMapper.insertSelective(templateSelective);
    }

    /**
     * 根据id，选择性更新
     * @param templateSelective
     * @return
     */
    public int updateTemplate(PerformanceEvaluationTemplate templateSelective){
        return templateMapper.updateByPrimaryKeySelective(templateSelective);
    }

    /**
     * 根据id，删除考核模板
     * @param id
     * @return
     */
    public int deleteTemplate(String id){
        return templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id，获取考核模板
     * @param id
     * @return
     */
    public PerformanceEvaluationTemplate getTemplateById(String id){
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据岗位id，获取绩效考核模板
     * @param jobId 岗位id
     * @return
     */
    public List<PerformanceEvaluationTemplate> listTemplateByJobId(String jobId){
        PerformanceEvaluationTemplateExample example = new PerformanceEvaluationTemplateExample();
        PerformanceEvaluationTemplateExample.Criteria c = example.createCriteria();
        c.andJobIdEqualTo(jobId);

        return templateMapper.selectByExample(example);
    }
}
