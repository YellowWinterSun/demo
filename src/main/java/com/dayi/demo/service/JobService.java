package com.dayi.demo.service;

import com.dayi.demo.model.Job;
import com.dayi.demo.msgEnum.ResStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * 岗位相关的服务接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/17
 */
public interface JobService {

    /**
     * 返回所有岗位信息，按照部门排序
     * @return
     */
    List<Job> listJobOrderByDepartment();

    /**
     * 根据id获取岗位信息
     * @param jobId
     * @return 没有则null
     */
    Job getJob(String jobId);

    /**
     * 岗位管理中job表格数据的展示服务，不需要进行搜索的条件置为NULL
     * @param name 岗位名称（模糊搜索）
     * @param departmentName 部门名称（模糊搜索）
     * @param limitStart 分页起始
     * @param limitEnd 分页数据
     * @param orderByClause 排序规则
     * @return （1）“list”：符合条件的user集合；（2）“total”：符合条件的总数（未分页的记录数）
     */
    Map<String, Object> listJobTable(String name, String departmentName, Integer limitStart,
                                     Integer limitEnd, String orderByClause);

    /**
     * 增加一个新岗位
     * @param jobSelective
     * @return
     */
    boolean addJob(Job jobSelective);

    /**
     * 删除一个岗位
     * @param jobId
     * @param jobName
     * @return
     */
    boolean deleteJob(String jobId, String jobName);

    /**
     * 更新岗位信息，根据岗位ID (如果部门需要改变，则需同时传入部门id和name)
     * @param jobSelective 有选择的Job对象（ID属性必须填入）
     * @return 成功返回null，失败则返回对应枚举
     */
    ResStatusEnum updatejob(Job jobSelective);
}
