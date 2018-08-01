package com.dayi.demo.dao;

import com.dayi.demo.mapper.DepartmentMapper;
import com.dayi.demo.mapper.sql.DepartmentMapperSql;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.DepartmentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门表的Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
@Repository
public class DepartmentDao {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentMapperSql departmentMapperSql;

    /**
     * 根据部门负责人工号，清除所有是该负责人的部门的部门负责人字段
     * @param majordemo 即将剥夺部门负责人权限的用户工号
     */
    public void updateDepartmentMajordomoSetNull(String majordemo){
        departmentMapperSql.updateDepartmentMajordomoSetNull(majordemo);
    }

    /**
     * 获取系统中所有部门对象（按父级部门排序)
     * @return 所有部门对象
     */
    public List<Department> listDepartment(){
        DepartmentExample example = new DepartmentExample();
        example.setOrderByClause("parent_id ASC");

        return departmentMapper.selectByExample(example);
    }

    /**
     * 根据父级部门id集合，查询部门
     * @param listIds 父级部门ID集合
     * @return
     */
    public List<Department> listDepartmentByParentIds(List<String> listIds){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria c = example.createCriteria();
        c.andParentIdIn(listIds);

        return departmentMapper.selectByExample(example);
    }

    /**
     * 根据部门名获取部门信息
     * @param name 部门名称
     * @return
     */
    public Department getDepartmentByName(String name){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria c = example.createCriteria();
        c.andNameEqualTo(name);

        List<Department> list = departmentMapper.selectByExample(example);
        if (list.size() <= 0)
            return null;
        return list.get(0);
    }

    /**
     * 根据id获取部门信息
     * @param id
     * @return
     */
    public Department getDepartmentById(String id){
        return departmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据父级部门id，获取该父级部门的所有直属子部门
     * @param parentId 父级ID，如果传入空或者NULL则返回1级部门
     * @return
     */
    public List<Department> listDepartmentByParentId(String parentId){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria c = example.createCriteria();

        if (StringUtils.isBlank(parentId)){
            c.andParentIdIsNull();
        } else {
            c.andParentIdEqualTo(parentId);
        }

        return departmentMapper.selectByExample(example);
    }

    /**
     * 新增部门
     * @param department
     * @return
     */
    public int addDepartment(Department department){
        return departmentMapper.insertSelective(department);
    }

    /**
     * 根据id更新部门信息（会根据传入的对象，全部属性更新）
     * @param department 注意非selective
     * @return
     */
    public int updateDepartment(Department department){
        return departmentMapper.updateByPrimaryKey(department);
    }

    /**
     * 根据id删除部门
     * @param id 要删除的部门id
     * @return
     */
    public int deleteDepartment(String id){
        return departmentMapper.deleteByPrimaryKey(id);
    }

    /**
     * （全表更新）根据旧的父级部门id，更新为新的父级部门id
     * @param oldId 旧id
     * @param newId 新id
     * @return
     */
    public int updateDepartmentParentId(String oldId, String newId){
        if (null == newId || newId.equals("")){
            return departmentMapperSql.updateDepartmentParentIdSetNull(oldId);
        }
        return departmentMapperSql.updateDepartmentParentId(oldId, newId);
    }

    /**
     * 根据部门总监工号，获取对应的部门
     * @param majordomo 部门总监工号
     * @return
     */
    public List<Department> listByMajordomo(String majordomo){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria c = example.createCriteria();
        c.andMajordomoEqualTo(majordomo);
        return departmentMapper.selectByExample(example);
    }
}
