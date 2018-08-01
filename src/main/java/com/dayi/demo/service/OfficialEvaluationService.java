package com.dayi.demo.service;

import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.modelEnum.OfficialEvaluationEnum;

import java.util.List;
import java.util.Map;

/**
 * 负责绩效考核表的服务
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/28
 */
public interface OfficialEvaluationService {

    /**
     * 根据条件，查询绩效考核表（不需要查询的条件置为NULL）
     * @param userNo 考核人工号
     * @param status 状态
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return "list":List<OfficialPerformanceEvaluation>  ;  "total":不分页时的总数  ; "rows":当前记录数量
     */
    Map<String, Object> listOfficialEvaluation(String userNo, String status, Integer limitStart,
                                               Integer limitEnd, String order, String sort);

    /**
     * 根据id，获取绩效考核表信息
     * @param officialId 绩效考核表id
     * @return
     */
    OfficialPerformanceEvaluation getOfficialEvaluationById(String officialId);

    /**
     * 获取绩效考核表的考核项目信息（按照考核维度降序排序)
     * @param officialId 绩效考核表id
     * @return
     */
    List<OfficialItemRes> listOfficialItemResByOfficialId(String officialId);

    /**
     * 根据条件，查询下属的绩效考核表（不需要的条件置NULL）
     * 除了能查到现在的下属考核表外，还能查询到以前自己处理过的绩效考核表。
     * @param listUserNos 选中的下属工号集合
     * @param myNo 我的工号（用于人力行政工号使用）
     * @param year 筛选的年份
     * @param month 筛选的月份
     * @param userName 下属名称（模糊搜索）
     * @param userNo 下属工号（模糊搜索）
     * @param limitStart 分页起始
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序规则
     * @return
     */
    Map<String, Object> listMyChildOfficialEvaluation(List<String> listUserNos, String myNo, Integer year, Integer month
            , String userName, String userNo, Integer limitStart, Integer limitEnd, String order, String sort);

    /**
     * 根据条件，搜索绩效考核表
     * @param year 年份
     * @param month 月份
     * @param userName 考核人姓名（模糊搜索）
     * @param userNo 考核人工号（模糊搜索）
     * @param status 考核状态
     * @param jobName 岗位
     * @param departmentNames 部门集合
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序规则
     * @return
     */
    Map<String, Object> listOfficialEvaluationRes(Integer year, Integer month, String userName, String userNo, String status,
                                               String jobName, List<String> departmentNames, Integer limitStart, Integer limitEnd,
                                               String order, String sort);
}
