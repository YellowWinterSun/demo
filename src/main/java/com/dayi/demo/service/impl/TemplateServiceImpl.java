package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.EvaluationItemRes;
import com.dayi.demo.controller.model.TemplateItemRes;
import com.dayi.demo.controller.model.TemplateRes;
import com.dayi.demo.dao.DimensionalityDao;
import com.dayi.demo.dao.EvaluationItemDao;
import com.dayi.demo.dao.PerformanceEvaluationTemplateDao;
import com.dayi.demo.dao.TemplateToItemDao;
import com.dayi.demo.model.*;
import com.dayi.demo.service.JobService;
import com.dayi.demo.service.TemplateService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 绩效考核模板管理的服务实现类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    //绩效考核模板Dao
    @Autowired
    private PerformanceEvaluationTemplateDao templateDao;
    //绩效考核模板 - 考核项目 - 中间表Dao
    @Autowired
    private TemplateToItemDao templateToItemDao;
    //考核项目Dao
    @Autowired
    private EvaluationItemDao evaluationItemDao;
    //考核维度表Dao
    @Autowired
    private DimensionalityDao dimensionalityDao;

    //岗位相关服务类
    @Autowired
    private JobService jobService;

    /**
     * 绩效考核模板管理中  表格数据的展示服务，不需要进行搜索的条件置为null
     *
     * @param name          绩效考核模板表名（模糊搜索）
     * @param jobId         岗位ID
     * @param limitStart    分页开始
     * @param limitEnd      分页记录数
     * @param orderByClause 排序规则
     * @return （1）“list”：符合条件的TemplateRes对象集合；（2）“total”：符合条件的总数 (3)"rows":当前集合数量
     */
    @Override
    public Map<String, Object> listTemplate(String name, String jobId, Integer limitStart, Integer limitEnd, String orderByClause) {

        List<PerformanceEvaluationTemplate> listTemplate = templateDao.listTemplate(name, jobId, limitStart, limitEnd, orderByClause);

        //获取当前记录总数
        int total = templateDao.countTemplate(name, jobId);

        //将PerformanceEvaluationTemplate解析为TemplateRes对象
        List<TemplateRes> listRes = new ArrayList<>();
        for(PerformanceEvaluationTemplate obj : listTemplate){
            //根据岗位ID获取岗位名称
            String objJobId = obj.getJobId();
            String objJobName = "";

            Job nowJob = jobService.getJob(objJobId);
            if (null != nowJob){
                objJobName = jobService.getJob(objJobId).getName();
            }

            int weight = this.countTemplateWeight(obj.getId());

            TemplateRes templateRes = new TemplateRes(obj, objJobName, weight);
            listRes.add(templateRes);
        }

        //封装结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", listRes);
        resultMap.put("total", total);
        resultMap.put("rows", listRes.size());

        return resultMap;
    }

    /**
     * 统计某绩效考核模板的权重总分
     * @param id 绩效考核模板ID
     * @return 0-100
     */
    @Override
    public int countTemplateWeight(String id) {
        //先判断绩效考核模板id，是否有考核项目，没有的话返回0
        List<TemplateToItem> list = templateToItemDao.listTemplateToItemByTemplateId(id);
        if(list.size() <= 0){
            return 0;
        }

        return templateToItemDao.countTemplateWeight(id);
    }

    /**
     * 根据特定条件，搜索考核项目信息（默认以考核维度排序）
     * @param name             考核项目名称（模糊）
     * @param dimensionalityId 考核维度ID
     * @return 二次封装的考核项目返回体对象集合
     */
    @Override
    public List<EvaluationItemRes> listEvaluationItem(String name, String dimensionalityId) {
        //获取考核项目对象
        List<EvaluationItem> listItem = evaluationItemDao.listItem(name, dimensionalityId);

        //对考核项目进行二次封装
        List<EvaluationItemRes> listRes = new ArrayList<>();
        for(EvaluationItem item : listItem){
            //获取对应的考核维度对象
            Dimensionality dimensionality = dimensionalityDao.getDimensionalityById(item.getDimensionalityId());

            EvaluationItemRes res = new EvaluationItemRes(item, dimensionality.getName(), dimensionality.getLevel());
            listRes.add(res);
        }

        return listRes;
    }

    @Override
    public List<Dimensionality> listDimensionality() {
        return dimensionalityDao.listDimensionalityOrderbyLevel();
    }

    @Override
    public EvaluationItem getItemById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }

        return evaluationItemDao.getItemById(id);
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public boolean deleteItem(String id) throws RuntimeException{

        //(1)先删除中间表
        templateToItemDao.deleteByItemId(id);

        //(2)删除考核项目
        evaluationItemDao.deleteItemById(id);


        return true;
    }

    @Override
    public boolean updateItem(EvaluationItem itemSelective) {
        //如果没有设置id
        if (null == itemSelective || StringUtils.isBlank(itemSelective.getId())){
            return false;
        }

        //如果没有设置更新时间
        if (null == itemSelective.getUpdatetime()){
            itemSelective.setUpdatetime(new Date());
        }

        evaluationItemDao.updateItemById(itemSelective);

        return true;
    }

    @Override
    public boolean addItem(EvaluationItem item) {
        if (null == item){
            return false;
        }

        if (StringUtils.isBlank(item.getId())){
            item.setId(UUIDUtil.getUUID());
        }

        try {
            evaluationItemDao.addItem(item);
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public boolean addTemplate(PerformanceEvaluationTemplate template, List<String> itemIds) {
        if (null == template.getId()){
            template.setId(UUIDUtil.getUUID());
        }

        //增加新的绩效考核模板
        templateDao.addTemplate(template);

        //关联模板和项目
        if (null == itemIds || itemIds.size() <= 0){
            return true;
        }

        for (String itemId : itemIds){
            int num = templateToItemDao.add(template.getId(), itemId);
            if (num <= 0){
                return false;
            }
        }

        return true;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void updateTemplate(PerformanceEvaluationTemplate templateSelective, List<String> itemIds) throws RuntimeException {
        if (null == templateSelective.getUpdatetime()){
            templateSelective.setUpdatetime(new Date());
        }

        //更新绩效考核模板表
        templateDao.updateTemplate(templateSelective);

        //删除原来的对用的考核项目
        templateToItemDao.deleteByTemplateId(templateSelective.getId());

        //增加新的考核项目
        if (null != itemIds && itemIds.size() > 0){
            for (String itemId : itemIds){
                templateToItemDao.add(templateSelective.getId(), itemId);
            }
        }


    }

    @Override
    public void deleteTemplate(String id) {
        //删除考核模板
        templateDao.deleteTemplate(id);

        //删除中间表
        templateToItemDao.deleteByTemplateId(id);

    }

    @Override
    public TemplateRes getTemplateResById(String id) {
        //获取考核模板信息
        PerformanceEvaluationTemplate template = templateDao.getTemplateById(id);
        //二次封装考核模板
        Job job = jobService.getJob(template.getJobId());

        TemplateRes templateRes = new TemplateRes(template, null == job ? "" : job.getName(), job.getDepartmentName());
        int weight = this.countTemplateWeight(templateRes.getId());
        templateRes.setWeight(weight);

        return templateRes;
    }

    @Override
    public List<TemplateItemRes> getTemplateItemResById(String id) {
        //获取当前考核模板的考核项目列表（按考核维度优先级降序排序)
        List<EvaluationItem> listItem = evaluationItemDao.listItem(id);

        //二次封装考核项目信息
        List<TemplateItemRes> listResult = new ArrayList<>();
        //记录考核维度个数
        Map<String, Integer> map = new HashMap<>();

        for (EvaluationItem item : listItem){
            TemplateItemRes res = new TemplateItemRes();

            //获取考核维度名
            Dimensionality dimensionality = dimensionalityDao.getDimensionalityById(item.getDimensionalityId());

            if (!map.containsKey(item.getDimensionalityId())){
                //Map中不存在当前键名
                //设置当前考核项目为考核维度的第一个
                res.setFirst(true);
                //记录个数
                Integer total = 1;
                map.put(item.getDimensionalityId(), total);
            } else {
                //Map中存在当前健名
                res.setFirst(false);

                //计数器+1
                Integer total = map.get(item.getDimensionalityId());
                map.put(item.getDimensionalityId(), ++total);
            }

            res.setId(item.getId());
            res.setName(item.getName());
            res.setWeight(item.getWeight().intValue());
            res.setContent(item.getContent());
            res.setDimensionalityId(item.getDimensionalityId());
            res.setDimensionalityName(dimensionality.getName());

            listResult.add(res);
        }

        //设置维度项目数量
        for (TemplateItemRes res : listResult){
            if (res.isFirst()){
                Integer total = map.get(res.getDimensionalityId());
                res.setItemNum(total);
            }
        }

        return listResult;
    }

    @Override
    public List<PerformanceEvaluationTemplate> listTemplateByJobId(String jobId) {
        if (StringUtils.isBlank(jobId)){
            return new ArrayList<PerformanceEvaluationTemplate>();
        }

        return templateDao.listTemplateByJobId(jobId);
    }


}
