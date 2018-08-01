package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.OfficialEvaluationRes;
import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.dao.JobDao;
import com.dayi.demo.dao.OfficialEvaluationItemDao;
import com.dayi.demo.dao.OfficialPerformanceEvaluationDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.*;
import com.dayi.demo.service.OfficialEvaluationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 绩效考核表服务类的实现
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/28
 */
@Service
public class OfficialEvaluationServiceImpl implements OfficialEvaluationService {

    @Autowired
    private OfficialEvaluationItemDao officialEvaluationItemDao;
    @Autowired
    private OfficialPerformanceEvaluationDao officialPerformanceEvaluationDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JobDao jobDao;

    /**
     * 根据条件，查询绩效考核表（不需要查询的条件置为NULL）
     *
     * @param userNo     考核人工号
     * @param status     状态
     * @param limitStart 分页起始下标
     * @param limitEnd   分页记录数
     * @param order      排序字段
     * @param sort       排序方式
     * @return
     */
    @Override
    public Map<String, Object> listOfficialEvaluation(String userNo, String status,
                                                      Integer limitStart, Integer limitEnd,
                                                      String order, String sort) {
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();

        if (StringUtils.isNotBlank(userNo)){
            c.andUserNoEqualTo(userNo);
        }
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        //获得符合条件的对象集合
        List<OfficialPerformanceEvaluation> list = officialPerformanceEvaluationDao.listByExampleLimit(example, limitStart, limitEnd);
        //统计不分页情况下的记录总数
        Integer total = 0;
        if (null == limitStart){
            //没有使用分页
            total = list.size();
        }
        else {
            total = officialPerformanceEvaluationDao.countByExample(example);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("total", total);
        resultMap.put("rows", list.size());

        return resultMap;
    }

    /**
     * 根据id，获取绩效考核表信息
     *
     * @param officialId 绩效考核表id
     * @return
     */
    @Override
    public OfficialPerformanceEvaluation getOfficialEvaluationById(String officialId) {
        return officialPerformanceEvaluationDao.getById(officialId);
    }

    /**
     * 获取绩效考核表的考核项目信息（按照考核维度降序排序)
     *
     * @param officialId 绩效考核表id
     * @return
     */
    @Override
    public List<OfficialItemRes> listOfficialItemResByOfficialId(String officialId) {
        //获取当前考核项目列表（按考核维度优先级降序排序）
        List<OfficialEvaluationItem> listItem = officialEvaluationItemDao.listOfficialItem(officialId);


        //二次封装考核项目信息
        List<OfficialItemRes> listResult = new ArrayList<>();
        //记录考核维度个数
        Map<String, Integer> map = new HashMap<>();

        for (OfficialEvaluationItem item : listItem){
            OfficialItemRes res = new OfficialItemRes(item);

            if (!map.containsKey(item.getDimensionalityName())){
                //Map中不存在这个考核维度
                //设置当前考核项目为该考核维度的第一个
                res.setFirst(true);
                //记录个数
                Integer total = 1;
                map.put(item.getDimensionalityName(), total);
            } else {
                //Map中存在这个考核维度
                res.setFirst(false);

                //计数器+1
                Integer total = map.get(item.getDimensionalityName());
                map.put(item.getDimensionalityName(), ++total);
            }

            listResult.add(res);
        }

        //设置考核维度的项目数量
        for (OfficialItemRes res : listResult){
            if (res.isFirst()){
                Integer total = map.get(res.getDimensionalityName());
                res.setItemNum(total);
            }
        }

        return listResult;
    }

    @Override
    public Map<String, Object> listMyChildOfficialEvaluation(List<String> listUserNos, String myNo
            , Integer year, Integer month, String userName, String userNo
            , Integer limitStart, Integer limitEnd, String order, String sort) {

        //获取现在的所有下属 绩效考核表
        List<OfficialPerformanceEvaluation> list1 = new ArrayList<>();
        if (!listUserNos.isEmpty()){
            list1 = officialPerformanceEvaluationDao.listByUserNos(listUserNos, null);
        }

        //获取以前自己处理过的绩效考核表
        List<OfficialPerformanceEvaluation> list2 = officialPerformanceEvaluationDao.listHistoryByNo(myNo);

        //将上述绩效考核表id组合起来，去除重复
        Set<String> setIds = new HashSet<>();
        for (OfficialPerformanceEvaluation data : list1){
            setIds.add(data.getId());
        }
        for (OfficialPerformanceEvaluation data : list2){
            setIds.add(data.getId());
        }

        if (setIds.isEmpty()){
            //空，既没有下属的考核表，也没有自己处理过的考核表
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("list", new ArrayList<>());
            resultMap.put("total", 0);
            resultMap.put("rows", 0);

            return resultMap;
        }

        //Set转List
        List<String> listIds = new ArrayList<>(setIds);

        //封装条件
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();
        c.andIdIn(listIds);

        if (null != year){
            c.andYearEqualTo(year.shortValue());
        }
        if (null != month){
            c.andMonthEqualTo(month.byteValue());
        }
        if (StringUtils.isNotBlank(userName)){
            c.andUserNameLike("%" + userName + "%");
        }
        if (StringUtils.isNotBlank(userNo)){
            c.andUserNoLike("%" + userNo + "%");
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        //带分页的结果集合
        List<OfficialPerformanceEvaluation> listResult = officialPerformanceEvaluationDao.listByExampleLimit(example, limitStart, limitEnd);
        //统计不分页情况下的记录总数
        Integer total = 0;
        if (null == limitStart){
            //没有使用分页
            total = listResult.size();
        }
        else {
            total = officialPerformanceEvaluationDao.countByExample(example);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", listResult);
        resultMap.put("total", total);
        resultMap.put("rows", listResult.size());

        return resultMap;

    }

    @Override
    public Map<String, Object> listOfficialEvaluationRes(Integer year, Integer month, String userName, String userNo
            , String status, String jobName, List<String> departmentNames
            , Integer limitStart, Integer limitEnd, String order, String sort) {

        //封装条件
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();

        if (year != null){
            c.andYearEqualTo(year.shortValue());
        }
        if (month != null){
            c.andMonthEqualTo(month.byteValue());
        }
        if (StringUtils.isNotBlank(userName)){
            c.andUserNameLike("%" + userName + "%");
        }
        if (StringUtils.isNotBlank(userNo)){
            c.andUserNoLike("%" + userNo + "%");
        }
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(jobName)){
            c.andJobNameEqualTo(jobName);
        }
        if (null != departmentNames && !departmentNames.isEmpty()){
            c.andDepartmentNameIn(departmentNames);
        }
        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        //获取分页的结果集合
        List<OfficialPerformanceEvaluation> listResult = officialPerformanceEvaluationDao.listByExampleLimit(example, limitStart, limitEnd);
        //统计不分页情况下的记录总数
        Integer total = 0;
        if (null == limitStart){
            //没有使用分页
            total = listResult.size();
        }
        else {
            total = officialPerformanceEvaluationDao.countByExample(example);
        }

        //二次封装
        List<OfficialEvaluationRes> listRes = new ArrayList<>();
        for (OfficialPerformanceEvaluation data : listResult){
            //获取薪资类别和入职时间
            String salaryType = null;
            Date entryDate = null;

            User nowUser = userDao.getUserByNo(data.getUserNo());
            //如果找不到用户信息，说明用户已经离职了，这个绩效考核表是历史记录
            if (null != nowUser){
                //用户记录仍在
                entryDate = nowUser.getEntrydate();
                Job nowJob = jobDao.getJob(nowUser.getJobId());
                salaryType = nowJob.getSalaryType();
            }

            OfficialEvaluationRes res = new OfficialEvaluationRes(data, salaryType, entryDate);
            listRes.add(res);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", listRes);
        resultMap.put("total", total);
        resultMap.put("rows", listRes.size());

        return resultMap;

    }
}
