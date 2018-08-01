package com.dayi.demo.service.impl;

import com.dayi.demo.dao.JobDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.Job;
import com.dayi.demo.model.JobExample;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.JobService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位服务类的实现
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/17
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 返回所有岗位信息，按照部门排序
     *
     * @return
     */
    @Override
    public List<Job> listJobOrderByDepartment() {
        return jobDao.listJobOrderByDepartment();
    }

    /**
     * 根据id获取岗位信息
     * @param jobId 岗位id
     * @return 没有则null
     */
    @Override
    public Job getJob(String jobId) {
        if(StringUtils.isNotBlank(jobId)){
            return jobDao.getJob(jobId);
        }
        return null;
    }

    /**
     * 岗位管理中job表格数据的展示服务，不需要进行搜索的条件置为NULL
     *
     * @param name           岗位名称（模糊搜索）
     * @param departmentName 部门名称（模糊搜索）
     * @param limitStart     分页起始
     * @param limitEnd       分页数据
     * @param orderByClause  排序规则
     * @return （1）“list”：符合条件的user集合；（2）“total”：符合条件的总数（未分页的记录数）
     */
    @Override
    public Map<String, Object> listJobTable(String name, String departmentName, Integer limitStart, Integer limitEnd, String orderByClause) {
        List<Job> list = jobDao.listJob(name, departmentName, limitStart, limitEnd, orderByClause);

        //获取当前条件的记录总数
        JobExample example = new JobExample();
        JobExample.Criteria c = example.createCriteria();
        if (null != name){
            c.andNameLike("%" + name + "%");
        }
        if (null != departmentName){
            c.andDepartmentNameLike("%" + departmentName + "%");
        }
        int total = jobDao.countByExample(example);

        //封装结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("total", total);
        resultMap.put("rows", list.size());

        return resultMap;
    }

    /**
     * 增加一个新岗位
     *
     * @param jobSelective
     * @return
     */
    @Override
    public boolean addJob(Job jobSelective) {
        if (null == jobSelective){
            return false;
        }

        //如果未设置UUID，则自动设置
        if(StringUtils.isBlank(jobSelective.getId())){
            jobSelective.setId(UUIDUtil.getUUID());
        }

        try {
            jobDao.addJob(jobSelective);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteJob(String jobId, String jobName) {
        int valid = userService.countUserByJob(jobId, jobName);
        if(valid > 0){
            return false;
        }

        int num = jobDao.deleteJob(jobId, jobName);
        return (num > 0) ? true : false;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public ResStatusEnum updatejob(Job jobSelective){
        //校验id项
        if (StringUtils.isBlank(jobSelective.getId())){
            return ResStatusEnum.DATA_NULL;
        }

        //如果更新时间没有设置，则默认以当前更新时间
        if (null == jobSelective.getUpdatetime()){
            jobSelective.setUpdatetime(new Date());
        }

        //如果要更新岗位名称，则需要进行查重处理
        if(null != jobSelective.getName()){
            JobExample example = new JobExample();
            JobExample.Criteria c = example.createCriteria();
            c.andNameEqualTo(jobSelective.getName());
            c.andIdNotEqualTo(jobSelective.getId());

            int validNum = jobDao.countByExample(example);
            if (validNum > 0){
                return ResStatusEnum.JOB_NAME_REPEAT;
            }
        }

        //更新Job表
        jobDao.updateJob(jobSelective);

        //如果更新了岗位名称，则需要改动user表对应的岗位名称字段
        if (null != jobSelective.getName()){
            userDao.updateUserJobNameByJobId(jobSelective.getId(), jobSelective.getName());
        }

        //如果更新了岗位对应的部门，则需要改动user表的部门字段
        if (null != jobSelective.getDepartmentId()){
            userDao.updateUserDepartmentNameByJobId(jobSelective.getId(), jobSelective.getDepartmentName());
        }

        return null;
    }


}
