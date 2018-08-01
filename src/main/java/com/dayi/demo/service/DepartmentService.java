package com.dayi.demo.service;

import com.dayi.demo.mapper.DepartmentMapper;
import com.dayi.demo.model.Department;
import com.dayi.demo.msgEnum.ResStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * 部门服务类的接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
public interface DepartmentService {

    /**
     * 获取系统中所有部门信息
     * @return
     */
    List<Department> listDepartment();

    /**
     * 根据部门总监工号，获取其部门
     * @param majordomo 部门总监工号
     * @return
     */
    List<Department> listByMajordomo(String majordomo);

    /**
     * 根据部门id，获取其所有下级部门（递归下去，直到最低端）
     * @param departmentId
     * @return （返回的部门对象，不包括自己)
     */
    List<Department> listByParentDepartmentRecurve(String departmentId);

    /**
     * 获取部门层级结构树
     *
     * @param departmentName
     * @return (1)"listQueue"：以当前部门为基准，所有直属父部门<br/>
     * (2)“listSame”：所有同级部门
     * （3）“listClild”：所有直属子部门
     */
    Map<String, Object> getDepartmentTree(String departmentName);

    /**
     * 根据ID获取部门信息
     * @param id uuid
     * @return
     */
    Department getDepartmentById(String id);

    /**
     * 根据名字获取部门信息
     * @param name
     * @return
     */
    Department getDepartmentByName(String name);

    /**
     * 校验部门名字是否存在数据库中
     * @param departmentName 部门名称(务必非空)
     * @return 名字存在true，不存在false
     */
    boolean validDepartmentNameRepeat(String departmentName);

    /**
     * 增加新部门
     * @param department
     * @return
     */
    boolean addDepartment(Department department);

    /**
     * 更新部门信息（根据ID）
     * @param departmentSelective 选择性更新（null表示不更新对应的字段）
     * @return 成功返回null，失败返回对应枚举
     */
    ResStatusEnum updateDepartment(Department departmentSelective) throws Exception;

    /**
     * 根据部门id删除部门
     * @param id 部门id
     * @return
     */
    boolean deleteDepartment(String id);
}
