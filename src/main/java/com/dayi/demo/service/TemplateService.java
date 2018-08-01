package com.dayi.demo.service;

import com.dayi.demo.controller.model.EvaluationItemRes;
import com.dayi.demo.controller.model.TemplateItemRes;
import com.dayi.demo.controller.model.TemplateRes;
import com.dayi.demo.model.Dimensionality;
import com.dayi.demo.model.EvaluationItem;
import com.dayi.demo.model.PerformanceEvaluationTemplate;

import java.util.List;
import java.util.Map;

/**
 * 绩效考核模板的服务接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
public interface TemplateService {

    /**
     * 绩效考核模板管理中  表格数据的展示服务，不需要进行搜索的条件置为null
     * @param name 绩效考核模板表名（模糊搜索）
     * @param jobId 岗位ID
     * @param limitStart 分页开始
     * @param limitEnd 分页记录数
     * @param orderByClause 排序规则
     * @return （1）“list”：符合条件的TemplateRes对象集合；（2）“total”：符合条件的总数 (3)"rows":当前集合数量
     */
    Map<String, Object> listTemplate(String name, String jobId, Integer limitStart, Integer limitEnd, String orderByClause);

    /**
     * 统计某绩效考核模板的权重总分
     * @param id 绩效考核模板ID
     * @return 0-100
     */
    int countTemplateWeight(String id);

    /**
     * 根据特定条件，搜索考核项目信息（默认以考核维度排序）
     * @param name 考核项目名称（模糊）
     * @param dimensionalityId 考核维度ID
     * @return 二次封装的考核项目返回体对象集合
     */
    List<EvaluationItemRes> listEvaluationItem(String name, String dimensionalityId);

    /**
     * 获取全部考核维度信息
     * @return
     */
    List<Dimensionality> listDimensionality();

    /**
     * 根据考核项目id获取考核项目信息
     * @param id 考核项目UUID
     * @return
     */
    EvaluationItem getItemById(String id);

    /**
     * 根据 考核项目ID删除考核项目
     * @param id 考核项目id
     * @return
     */
    boolean deleteItem(String id);

    /**
     * 根据id，更新考核项目信息
     * @param itemSelective 考核项目
     * @return
     */
    boolean updateItem(EvaluationItem itemSelective);

    /**
     * 增加一个新的模板考核项目
     * @param item
     * @return
     */
    boolean addItem(EvaluationItem item);

    /**
     * 增加一个新的绩效考核模板
     * @param template 封装好的新模板对象
     * @param itemIds 考核项目id集合
     * @return
     */
    boolean addTemplate(PerformanceEvaluationTemplate template, List<String> itemIds);

    /**
     * 根据id，修改绩效考核模板以及考核项目（默认清除原来模板的考核项目，用新的替换）
     * @param templateSelective
     * @param itemIds 新的考核项目id集合
     * @return
     */
    void updateTemplate(PerformanceEvaluationTemplate templateSelective, List<String> itemIds);

    /**
     * 根据id删除考核模板
     * @param id
     */
    void deleteTemplate(String id);

    /**
     * 根据 模板id获取绩效考核模板
     * @param id
     * @return
     */
    TemplateRes getTemplateResById(String id);

    /**
     * 获取绩效考核模板的考核项目信息(按照考核维度优先级降序排列)
     * @param id 绩效考核模板id
     * @return
     */
    List<TemplateItemRes> getTemplateItemResById(String id);

    /**
     * 根据岗位id，获取它的绩效考核模板
     * @param jobId 岗位ID
     * @return
     */
    List<PerformanceEvaluationTemplate> listTemplateByJobId(String jobId);
}
