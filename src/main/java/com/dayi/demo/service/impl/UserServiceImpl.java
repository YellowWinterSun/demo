package com.dayi.demo.service.impl;

import com.dayi.demo.dao.DepartmentDao;
import com.dayi.demo.dao.OfficialPerformanceEvaluationDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.mapper.DepartmentMapper;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.User;
import com.dayi.demo.model.UserExample;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.DepartmentService;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

/**
 * 用户服务类
 *
 * @author  HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private OfficialPerformanceEvaluationDao officialPerformanceEvaluationDao;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;

    /**
     * 根据手机号码获取用户
     *
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        if (StringUtils.isBlank(phone)){
            return null;
        }

        return userDao.getUserByPhone(phone);
    }

    /**
     * 根据UUID获取用户
     *
     * @param id UUID
     * @return 没有则NULL
     */
    @Override
    public User getUserById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByNo(String no) {

        if (StringUtils.isBlank(no)){
            return null;
        }
        return userDao.getUserByNo(no);
    }

    /**
     * 用户管理中user表格数据的展示服务,不需要进行搜索的条件置NULL
     *
     * @param name          姓名（模糊搜索）
     * @param phone         电话号码（模糊搜索）
     * @param no            工号（模糊搜索）
     * @param limitStart    分页起始下标
     * @param limitEnd      记录个数
     * @param orderByClause 排序规则
     * @return （1）“list”：符合条件的user集合；（2）“total”：符合条件的总数（未分页的记录数）
     */
    @Override
    public Map<String, Object> listUserTable(String name, String phone, String no,
                                             Integer limitStart, Integer limitEnd, String orderByClause) {

        List<User> list = userDao.listUser(name, phone, no, limitStart, limitEnd, orderByClause);

        //获取当前条件的记录总数
        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        if (null != name){
            c.andNameLike("%" + name + "%");
        }
        if (null != phone){
            c.andPhoneLike("%" + phone + "%");
        }
        if (null != no) {
            c.andNoLike("%" + no + "%");
        }
        int total = userDao.countByExample(example);

        //封装结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("total", total);
        resultMap.put("rows", list.size());

        return resultMap;
    }

    @Override
    public List<User> listUser(List<String> listIds, String nameLike, Integer limitStart, Integer limitEnd, String order, String sort) {
        return userDao.listUserLimitExample(listIds, nameLike, limitStart, limitEnd, order, sort);
    }

    /**
     * 增加新用户
     *
     * @param newUser 封装好的新用户
     * @return false的情况：（1）工号，手机号码重复。（2）非空属性为NULL。（3）传入了null属性
     */
    @Override
    public boolean addUser(User newUser) {
        if (null == newUser){
            return false;
        }

        //校验是否重复手机号码和工号
        /*
        验证逻辑代码块1（暂废除）
        if (null != userDao.getUserByPhone(newUser.getPhone())){

            return false;
        }
        if (null != userDao.getUserByNo(newUser.getNo())){
            return false;
        }
        */
        UserExample validExample = new UserExample();
        //手机号
        UserExample.Criteria c = validExample.createCriteria();
        c.andPhoneEqualTo(newUser.getPhone());
        //工号
        UserExample.Criteria c2 = validExample.createCriteria();
        c2.andNoEqualTo(newUser.getNo());
        //逻辑or 手机号or工号
        validExample.or(c2);
        int validNum = userDao.countByExample(validExample);
        if (0 < validNum){
            //说明重复了
            return false;
        }

        //插入数据库操作
        try {
            //操作用户表
            int num = userDao.addUser(newUser);
            if(num <= 0) {
                return false;
            }
            //操作用户角色表(为新用户设置为默认初始角色)
            roleService.addRoleUser("60f1a73be94343268d01eec12ab31845", newUser.getId());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 根据id，工号，手机号码删除一个用户。不需要的条件置NULL，至少传入一个参数。
     * @param id UUID
     * @param no 工号
     * @param phone 手机号码
     * @return 成功与否
     */
    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public boolean deleteUser(String id, String phone, String no) throws RuntimeException{

        //先获取要删除的用户工号
        User deleteUser = null;
        if (StringUtils.isNotBlank(id)){
            deleteUser = userDao.getUserById(id);
        }
        else if (StringUtils.isNotBlank(phone)){
            deleteUser = userDao.getUserByPhone(phone);
        }
        else if (StringUtils.isNotBlank(no)){
            deleteUser = userDao.getUserByNo(no);
        }

        if(null == deleteUser){
            return false;
        }

        //先删除用户
        int num = userDao.deleteUser(id, phone, no);
        if (num <= 0) {
            throw new RuntimeException("删除的用户不存在");
        }

        //清除其他用户直属上司是当前用户的字段
        userDao.updateUserDirectSupervisorNoSetNull(deleteUser.getNo());

        //清除部门负责人项
        departmentDao.updateDepartmentMajordomoSetNull(deleteUser.getNo());

        //删除用户角色中间表
        roleService.deleteUserToRoleByUserId(deleteUser.getId());
        return true;
    }

    /**
     * 根据UUID，选择性更新用户信息（不允许更改工号）,如果传入password，无需加密
     * @param userSelective User对象
     * @return 失败返回状态码，成功返回null
     */
    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public ResStatusEnum updateUser(User userSelective) throws RuntimeException{
        //校验手机号码是否重复
        if (null == userSelective)
            return ResStatusEnum.DATA_NULL;
        if (null != userSelective.getPhone()){
            String newPhone = userSelective.getPhone();
            if (null != userDao.getUserByPhone(newPhone)){
                return ResStatusEnum.USER_PHONE_REPEAT;
            }
        }
        //工号不允许修改
        if (null != userSelective.getNo()){
           return ResStatusEnum.USER_NO_CANNOT_UPDATE;
        }
        if (null != userSelective.getDirectSupervisorNo()){
            //设置直属上司no
            if(userSelective.getDirectSupervisorNo().equals("")){
                //如果传入空字符串，则代表更新为无直属上司
                userDao.updateUserDirectSupervisorNoSetNullById(userSelective.getId());
                userSelective.setDirectSupervisorNo(null);
            }
        }

        //判断密码是否需要修改，需要的话进行MD5加密
        if(null != userSelective.getPassword()){
            String passwordMd5 = MD5Util.encode2hex(userSelective.getPassword());
            userSelective.setPassword(passwordMd5);
        }

        if (null == userSelective.getUpdatetime()){
            //如果未设置更新时间，则默认以当前时间为准
            userSelective.setUpdatetime(new Date());
        }

        int num = userDao.updateUser(userSelective);
        if(num <= 0){
            throw new RuntimeException();
        }

        //如果修改了姓名，则需要修改其他关联表的关联字段
        if(null != userSelective.getName()){
            //更新绩效考核正表的签名
            User updateUser = userDao.getUserById(userSelective.getId());
            String updateUserNo = updateUser.getNo();
            String updateUserName = updateUser.getName();

            officialPerformanceEvaluationDao.updateUserNameByNo(updateUserName, updateUserNo);
            officialPerformanceEvaluationDao.updateBoss1NameByNo(updateUserName, updateUserNo);
            officialPerformanceEvaluationDao.updateBoss2NameByNo(updateUserName, updateUserNo);
            officialPerformanceEvaluationDao.updateHrNameByNo(updateUserName, updateUserNo);
        }

        return null;
    }

    @Override
    public int countUserByJob(String jobId, String jobName) {

        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();

        if (StringUtils.isNotBlank(jobId)){
            c.andJobIdEqualTo(jobId);
        } else if (StringUtils.isNotBlank(jobName)){
            c.andJobNameEqualTo(jobName);
        } else {
            return 0;
        }

        return userDao.countByExample(example);
    }

    @Override
    public List<User> listUserByDirectSupervisorNo(String directSupervisorNo) {
        return userDao.listUserByDirectSupervisorNo(directSupervisorNo);
    }

    @Override
    public List<User> listUserByDepartments(List<String> departmentNames) {
        if (null == departmentNames || departmentNames.isEmpty()){
            return new ArrayList<>();
        }

        return userDao.listUserByDepartmentNames(departmentNames);
    }

    @Override
    public List<User> listUserByDepartmentMajordomo(String majordomoNo) {

        List<Department> listDepartment = departmentDao.listByMajordomo(majordomoNo);
        if (listDepartment.isEmpty()){
            //空则说明不是部门总监
            return new ArrayList<>();
        }

        List<String> listNames = new ArrayList<>();
        for (Department department : listDepartment){
            listNames.add(department.getName());
        }

        return this.listUserByDepartments(listNames);
    }

    @Override
    public List<User> listUserByDepartmentMajordomoRecurve(String majordomoNo) {
        //获取其是部门总监的部门集合
        List<Department> listDepartment = departmentDao.listByMajordomo(majordomoNo);
        if (listDepartment.isEmpty()){
            //说明不是部门总监
            return new ArrayList<>();
        }

        //查询每一个部门的所有子部门
        List<Department> listAllChild = new ArrayList<>();
        for (Department data : listDepartment){
            List<Department> list = departmentService.listByParentDepartmentRecurve(data.getId());
            if (!list.isEmpty()){
                listAllChild.addAll(list);
            }
        }

        List<String> listNames = new ArrayList<>();
        for (Department data : listDepartment){
            listNames.add(data.getName());
        }
        for (Department data : listAllChild){
            listNames.add(data.getName());
        }

        return this.listUserByDepartments(listNames);
    }

    @Override
    public List<User> listUser() {

        return userDao.listUser(null, null, null, null, null, null);
    }

    @Override
    public List<User> listUserNotInIds(List<String> userIds) {
        //如果传入为空，则返回系统全部用户信息
        if (null == userIds || userIds.isEmpty()){
            return this.listUser();
        }

        return userDao.listUserNotInIds(userIds);
    }

    @Override
    public boolean validBossIsUserBoss(String bossNo, String userNo) {

        //获取下属的信息
        User user = userDao.getUserByNo(userNo);
        //先判断是否为直属上司
        if (user.getDirectSupervisorNo().equals(bossNo)){
            return true;
        }

        //获取下属的部门信息
        Department department = departmentDao.getDepartmentByName(user.getDepartmentName());

        if (department.getMajordomo().equals(bossNo)){
            return true;
        }

        //开始判断是否为上级部门的负责人
        if (StringUtils.isBlank(department.getParentId())){
            //没有上级部门
            return false;
        }

        //记录父级部门信息
        Department parentDepartment = departmentDao.getDepartmentById(department.getParentId());
        while (null != parentDepartment){
            if (parentDepartment.getMajordomo().equals(bossNo)){
                return true;
            }
            if (StringUtils.isBlank(parentDepartment.getParentId())){
                //没有上级部门了
                break;
            }
            parentDepartment = departmentDao.getDepartmentById(parentDepartment.getParentId());
        }

        return false;
    }


}
