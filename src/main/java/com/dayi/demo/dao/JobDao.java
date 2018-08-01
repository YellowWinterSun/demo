package com.dayi.demo.dao;

import com.dayi.demo.mapper.JobMapper;
import com.dayi.demo.mapper.sql.JobMapperSql;
import com.dayi.demo.model.DepartmentExample;
import com.dayi.demo.model.Job;
import com.dayi.demo.model.JobExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Job表的Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/17
 */
@Repository
public class JobDao {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobMapperSql jobMapperSql;

    /**
     * 返回所有岗位信息，按照部门来排序
     * @return
     */
    public List<Job> listJobOrderByDepartment(){
        JobExample example = new JobExample();
        example.setOrderByClause("department_name ASC");

        return jobMapper.selectByExample(example);
    }

    /**
     * 根据id返回岗位信息
     * @param id
     * @return
     */
    public Job getJob(String id){
        return jobMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据特定条件进行模糊搜索
     * @param name 岗位名称（模糊搜索）
     * @param departmentName 部门名（模糊搜索）
     * @param limitStart 分页起始
     * @param limitEnd 分页数量
     * @param orderByClause 排序规则
     * @return 所有条件为NULL，默认返回全表
     */
    public List<Job> listJob(String name, String departmentName, Integer limitStart,
                               Integer limitEnd, String orderByClause){
        return jobMapper.listJob(name, departmentName, limitStart, limitEnd, orderByClause);
    }

    /**
     * 根据JobExample提供的条件，统计记录数
     * @param example
     * @return
     */
    public int countByExample(JobExample example){
        return jobMapper.countByExample(example);
    }

    /**
     * 增加一个新岗位
     * @param newJobSelective
     * @return
     */
    public int addJob(Job newJobSelective){
        return jobMapper.insertSelective(newJobSelective);
    }

    /**
     * 删除一个岗位
     * @param id 岗位id
     * @param jobName 岗位名称
     * @return
     */
    public int deleteJob(String id, String jobName){
        if (StringUtils.isNotBlank(id)){
            return jobMapper.deleteByPrimaryKey(id);
        }

        JobExample example = new JobExample();
        JobExample.Criteria c = example.createCriteria();
        c.andNameEqualTo(jobName);

        return jobMapper.deleteByExample(example);
    }

    /**
     * 更新岗位信息（根据主键，id必须传入）
     * @param jobSelective 有选择性的更改
     * @return
     */
    public int updateJob(Job jobSelective){
        return jobMapper.updateByPrimaryKeySelective(jobSelective);
    }

    /**
     * （全表修改）根据部门id，修改其部门名称
     * @param id 要匹配的部门id
     * @param newName 要修改的新部门名称
     * @return
     */
    public int updateJobDepartmentNameByDepartmentId(String id, String newName){
        return jobMapperSql.updateJobDepartmentNameByDepartmentId(id, newName);
    }

    /**
     * 根据部门id，获取其所有岗位
     * @param departmentId 部门id
     * @return
     */
    public List<String> listJobByDepartmentId(String departmentId){
        JobExample example = new JobExample();
        JobExample.Criteria c = example.createCriteria();
        c.andDepartmentIdEqualTo(departmentId);

        //获取当前部门的所有岗位信息
        List<Job> listJob = jobMapper.selectByExample(example);

        List<String> listId = new ArrayList<>();
        for(Job job : listJob){
            listId.add(job.getId());
        }

        return listId;
    }

    /**
     * 根据部门id或者部门名称，删除岗位
     * @param departmentId 部门id
     * @param departmentName 部门名称
     * @return
     */
    public int deleteJobByDepartment(String departmentId, String departmentName){
        JobExample example = new JobExample();
        JobExample.Criteria c = example.createCriteria();

        if (StringUtils.isNotBlank(departmentId)){
            c.andDepartmentIdEqualTo(departmentId);

            return jobMapper.deleteByExample(example);
        }
        c.andDepartmentNameEqualTo(departmentName);
        return jobMapper.deleteByExample(example);
    }
}
