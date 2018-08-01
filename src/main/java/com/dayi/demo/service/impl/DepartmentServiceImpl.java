package com.dayi.demo.service.impl;

import com.dayi.demo.dao.DepartmentDao;
import com.dayi.demo.dao.JobDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.User;
import com.dayi.demo.model.UserExample;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.DepartmentService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 部门服务接口的实现
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserDao userDao;

    /**
     * 获取系统中所有部门信息
     *
     * @return
     */
    @Override
    public List<Department> listDepartment() {
        return departmentDao.listDepartment();
    }

    @Override
    public List<Department> listByMajordomo(String majordomo) {
        return departmentDao.listByMajordomo(majordomo);
    }

    @Override
    public List<Department> listByParentDepartmentRecurve(String departmentId) {
        //记录结果
        List<Department> listResult = new ArrayList<>();
        //先获取其1级部门
        List<Department> listChild = departmentDao.listDepartmentByParentId(departmentId);

        if (listChild.isEmpty()){
            //没有分部门，直接返回
            return listResult;
        }

        //循环一层层查询所有分部门
        while (!listChild.isEmpty()){
            //将当前层级所有部门加入结果集
            listResult.addAll(listChild);

            //记录当前部门层级 的部门id
            List<String> listIds = new ArrayList<>();
            for (Department data : listChild){
                listIds.add(data.getId());
            }

            //查询下一层部门
            listChild = departmentDao.listDepartmentByParentIds(listIds);
        }

        return listResult;
    }

    /**
     * 获取部门层级结构树
     *
     * @param departmentName
     * @return (1)"listQueue"：以当前部门为基准，所有直属父部门<br/>
     * (2)“listSame”：所有同级部门
     * （3）“listClild”：所有直属子部门
     */
    @Override
    public Map<String, Object> getDepartmentTree(String departmentName) {
        //如果传入空，则默认返回所有1级部门
        if (StringUtils.isBlank(departmentName)){
            List<Department> listSameLevelDepartment = departmentDao.listDepartmentByParentId(null);
            Map<String, Object> map = new HashMap<>();
            map.put("listSame", listSameLevelDepartment);
            map.put("isFirst", true);   //是否所有1级部门
            return map;
        }

        //获取当前部门
        Department nowDepartment = departmentDao.getDepartmentByName(departmentName);

        //定义队列(以当前部门层级为基础，获取所有上级直属部门)
        List<Department> listQueue = new LinkedList<>();
        ((LinkedList<Department>) listQueue).addFirst(nowDepartment);
        if (StringUtils.isNotBlank(nowDepartment.getParentId())) {
            Department parentDepartment = departmentDao.getDepartmentById(nowDepartment.getParentId());

            //有父级部门,则一直循环下去
            while (null != parentDepartment){
                ((LinkedList<Department>) listQueue).addFirst(parentDepartment);

                if (StringUtils.isBlank(parentDepartment.getParentId())){
                    //没有父级了，则break
                    break;
                }
                parentDepartment = departmentDao.getDepartmentById(parentDepartment.getParentId());
            }
        }

        //获取同级直属部门
        List<Department> listSameLevelDepartment = departmentDao.listDepartmentByParentId(nowDepartment.getParentId());
        //获取当前部门的所有一级子部门
        List<Department> listChildLevelDepartment = departmentDao.listDepartmentByParentId(nowDepartment.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("listQueue", listQueue);
        map.put("listSame", listSameLevelDepartment);
        map.put("listChild", listChildLevelDepartment);
        map.put("isFirst", false);   //是否所有1级部门

        return map;
    }

    @Override
    public Department getDepartmentById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return departmentDao.getDepartmentById(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }

        return departmentDao.getDepartmentByName(name);
    }

    @Override
    public boolean validDepartmentNameRepeat(String departmentName) {
        Department department = departmentDao.getDepartmentByName(departmentName);
        if (null == department){
            //不存在记录，则没有重复
            return false;
        }
        return true;
    }

    @Override
    public boolean addDepartment(Department department) {
        //如果UUID未设置
        if (StringUtils.isBlank(department.getId())){
            department.setId(UUIDUtil.getUUID());
        }

        if (validDepartmentNameRepeat(department.getName())){
            //存在重名
            return false;
        }
        departmentDao.addDepartment(department);
        return true;
    }


    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public ResStatusEnum updateDepartment(Department departmentSelective) throws Exception{
        if (StringUtils.isBlank(departmentSelective.getId())){
            return ResStatusEnum.DATA_NULL;
        }

        Department oldDepartment = departmentDao.getDepartmentById(departmentSelective.getId());

        //校验部门名称是否有修改
        boolean validChangeName = false;
        String oldDepartmentName = oldDepartment.getName();

        if (null != departmentSelective.getName()){
            validChangeName = !oldDepartmentName.equals(departmentSelective.getName());
            if (validChangeName){
                //部门名称要修改，则需要进行查重
                if (this.validDepartmentNameRepeat(departmentSelective.getName())){
                    return ResStatusEnum.DEPARTMENT_NAME_REPEAT;
                }
                //设置新名
                oldDepartment.setName(departmentSelective.getName());
            }
        }

        //二次封装要更新的数据
        if (null != departmentSelective.getParentId() && "".equals(departmentSelective.getParentId())){
            //将父级部门id设置为NULL
            oldDepartment.setParentId(null);
        }
        else if (null != departmentSelective.getParentId()){
            //将父级别部门设置为对应的id
            oldDepartment.setParentId(departmentSelective.getParentId());
        }

        if (null != departmentSelective.getMajordomo() && "".equals(departmentSelective.getMajordomo())){
            //将部门负责人设置NULL
            oldDepartment.setMajordomo(null);
        } else if (null != departmentSelective.getMajordomo()){
            //将部门负责人设置为对应的值
            oldDepartment.setMajordomo(departmentSelective.getMajordomo());
        }

        if (null != departmentSelective.getUpdatetime()){
            //设置了更新时间
            oldDepartment.setUpdatetime(departmentSelective.getUpdatetime());
        }
        else {
            //未设置更新时间的话，自动为其设置
            oldDepartment.setUpdatetime(new Date());
        }

        //更新操作
        departmentDao.updateDepartment(oldDepartment);

        //部门名称修改了，需要修改关联表
        if (validChangeName){
            //更新job表对应的部门名称
            jobDao.updateJobDepartmentNameByDepartmentId(oldDepartment.getId(), oldDepartment.getName());

            //更新user表对应的部门名称
            userDao.updateUserDepartmentNameByDepartmentName(oldDepartmentName, oldDepartment.getName());
        }

        return null;

    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public boolean deleteDepartment(String id) {
        //(1)判断当前部门是否有员工，有的话无法删除部门
        List<String> listJobIds = jobDao.listJobByDepartmentId(id);
        if (listJobIds.size() > 0){
            UserExample example1 = new UserExample();
            UserExample.Criteria c = example1.createCriteria();
            c.andJobIdIn(listJobIds);

            int userNum = userDao.countByExample(example1);
            if (userNum > 0){
                return false;
            }
        }

        //(2)判断部门是否有子部门，有子部门的话需要进行处理
        Department nowDepartment = departmentDao.getDepartmentById(id);
        List<Department> listChild = departmentDao.listDepartmentByParentId(id);
        if (listChild.size() > 0 && null != nowDepartment.getParentId()){
            //有子部门，且有父部门的情况。将子部门的parentId设置为当前父部门
            departmentDao.updateDepartmentParentId(id, nowDepartment.getParentId());
        }
        else if (listChild.size() > 0){
            //有子部门，但无父部门的情况。将子部门全部变为1级部门
            departmentDao.updateDepartmentParentId(id, null);
        }
        //删除部门所有的岗位
        jobDao.deleteJobByDepartment(id, null);
        //删除部门
        departmentDao.deleteDepartment(id);

        return true;
    }


}
