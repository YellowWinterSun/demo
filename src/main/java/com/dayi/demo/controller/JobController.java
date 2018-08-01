package com.dayi.demo.controller;

import com.dayi.demo.model.Department;
import com.dayi.demo.model.Job;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.DepartmentService;
import com.dayi.demo.service.JobService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 部门，岗位相关控制器
 * 负责部门，岗位的相关业务
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/17
 */
@Controller
@RequestMapping("/sys/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    /**
     * 跳转部门岗位主页
     * @return
     */
    @RequestMapping
    public String toJobDepartmentManageJsp(){
        return "sys/jobDepartmentManage";
    }

    /**
     * 根据特定条件获取岗位信息（不需要的条件置为NULL）
     * @param name 岗位名称
     * @param departmentName 部门名称
     * @param limitStart 分页起始
     * @param limitEnd 记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    @RequestMapping("/listJob")
    @ResponseBody
    public Msg listJob(String name, String departmentName, Integer limitStart,
                       Integer limitEnd, String order, String sort){
        //校验和处理传入参数
        if (StringUtils.isBlank(name)){
            name = null;
        }
        if (StringUtils.isBlank(departmentName)){
            departmentName = null;
        }
        String orderByClause = null;
        if (StringUtils.isNotBlank(order)){
            orderByClause = order + " " + sort;
        }

        Map<String, Object> map = jobService.listJobTable(name, departmentName, limitStart, limitEnd, orderByClause);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 获取系统中所有部门数据
     * @return
     */
    @RequestMapping("/listDepartment")
    @ResponseBody
    public Msg listDepartment(){
        List<Department> list = departmentService.listDepartment();
        return Msg.success(null).add("list", list);
    }

    /**
     * 返回所有岗位信息（按照部门排序) 【无需校验权限】
     * @return 100状态码，并携带“list”信息
     */
    @RequestMapping("/listJobOrderByDepartment")
    @ResponseBody
    public Msg listJobOrderByDepartment(){
        List<Job> list = jobService.listJobOrderByDepartment();
        return Msg.success(null).add("list", list);
    }

    /**
     * 根据传入的部门名称，获取部门的级层树状结构。
     * @param departmentName 部门名
     * @return 100携带“listQueue”：直属延续父亲部门，“listSame”：同级部门
     * ，“listChild”：所有直属子部门，“isFirst”：是否一级部门
    */
    @RequestMapping("/getDepartmentTree")
    @ResponseBody
    public Msg getDepartmentTree(String departmentName){

        Map<String, Object> map = departmentService.getDepartmentTree(departmentName);

        Msg msg = Msg.success(null);
        msg.setMap(map);

        return msg;
    }

    /**
     * 增加一个新岗位
     * @param name 岗位名称
     * @param departmentId 所属部门id
     * @param salaryType 薪资类别
     * @return 成功100，失败200携带msg
     */
    @RequestMapping("/addJob")
    @ResponseBody
    public Msg addJob(String name, String departmentId, String salaryType){
        //校验参数
        if (StringUtils.isBlank(name) || StringUtils.isBlank(departmentId) || StringUtils.isBlank(salaryType)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装数据
        Job newJob = new Job();
        newJob.setId(UUIDUtil.getUUID());
        newJob.setName(name);
        newJob.setSalaryType(salaryType);

        Department department = departmentService.getDepartmentById(departmentId);
        newJob.setDepartmentId(department.getId());
        newJob.setDepartmentName(department.getName());

        boolean result = jobService.addJob(newJob);
        if(result){
            return Msg.success(null);
        }
        return Msg.error("操作失败！可能原因：(1)信息不完整(2)岗位名重复");
    }

    /**
     * 部门岗位管理 - 删除岗位
     * @param id 岗位ID
     * @return 成功100,失败其他
     */
    @RequestMapping("/deleteJob")
    @ResponseBody
    public Msg deleteJob(String id){
        if (StringUtils.isBlank(id)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //判断当前岗位是否有员工
        int num = userService.countUserByJob(id, null);
        if (num > 0){
            return Msg.error("当前岗位无法删除，请先处理当前岗位员工");
        }

        jobService.deleteJob(id, null);

        return Msg.success(null);
    }

    /**
     * 部门岗位管理 - 更新岗位
     * @param id 要更新的岗位id
     * @param name 新岗位名称
     * @param departmentId 新部门id
     * @param salaryType 薪资类型
     * @return 成功100，其余查看提示信息
     */
    @RequestMapping("/updateJob")
    @ResponseBody
    public Msg updateJob(String id, String name, String departmentId, String salaryType){
        //校验参数
        if (StringUtils.isBlank(id) || StringUtils.isBlank(name) || StringUtils.isBlank(salaryType)
              || StringUtils.isBlank(departmentId)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装
        Job job = new Job();
        job.setId(id);
        job.setName(name);
        job.setSalaryType(salaryType);

        Department  department = departmentService.getDepartmentById(departmentId);
        job.setDepartmentId(department.getId());
        job.setDepartmentName(department.getName());

        job.setUpdatetime(new Date());

        ResStatusEnum res = jobService.updatejob(job);
        if(null == res){
            //成功
            return Msg.success("更新成功");
        }
        return Msg.res(res);

    }

    /**
     * 部门岗位管理 - 新增部门
     * @param name 部门名称
     * @param parentId 父级部门ID
     * @param majordemo 部门负责人工号
     * @return
     */
    @RequestMapping("/addDepartment")
    @ResponseBody
    public Msg addDepartment(String name, String parentId, String majordomo){
        //校验参数
        if (StringUtils.isBlank(name)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装
        Department department = new Department();
        department.setId(UUIDUtil.getUUID());
        department.setName(name);
        if (StringUtils.isNotBlank(parentId)){
            department.setParentId(parentId);
        }
        if (StringUtils.isNotBlank(majordomo)){
            department.setMajordomo(majordomo);
        }

        boolean result = departmentService.addDepartment(department);
        if (result){
            return Msg.success(null);
        }
        return Msg.res(ResStatusEnum.DEPARTMENT_NAME_REPEAT);
    }

    /**
     * 部门岗位管理 - 根据部门名称获取部门信息
     * @param name
     * @return
     */
    @RequestMapping("/getDepartmentByName")
    @ResponseBody
    public Msg getDepartmentByName(String name){
        Department department = departmentService.getDepartmentByName(name);
        return Msg.success(null).add("department", department);
    }

    /**
     * 部门岗位管理 - 更新部门信息
     * @param id 要更新的部门ID
     * @param name 新部门名称
     * @param parentId 新父级别ID
     * @param majordomo 新部门负责人
     * @return
     */
    @RequestMapping("/updateDepartment")
    @ResponseBody
    public Msg updateDepartment(String id, String name, String parentId, String majordomo){
        //校验参数
        if (StringUtils.isBlank(id) || StringUtils.isBlank(name)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装
        Department departmentSelective = new Department();
        departmentSelective.setId(id);
        departmentSelective.setName(name);
        departmentSelective.setParentId(parentId);
        departmentSelective.setMajordomo(majordomo);
        departmentSelective.setUpdatetime(new Date());

        try {
            ResStatusEnum res = departmentService.updateDepartment(departmentSelective);

            if(null == res) {
                return Msg.success("更新成功");
            }
            return Msg.res(res);
        } catch (Exception e) {
            return Msg.error("更新失败");
        }
    }

    /**
     * 部门岗位管理 - 删除部门
     * @param id 部门id
     * @return
     */
    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public Msg deleteDepartment(String departmentName){
        //校验参数
        if (StringUtils.isBlank(departmentName)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //校验ID是否存在
        Department department = departmentService.getDepartmentByName(departmentName);
        if (null == department){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        boolean result = departmentService.deleteDepartment(department.getId());
        if (result){
            return Msg.success("删除部门成功");
        }
        return Msg.error("有用户的部门无法删除，请先处理用户");
    }
}
