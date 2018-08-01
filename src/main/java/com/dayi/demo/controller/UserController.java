package com.dayi.demo.controller;

import com.dayi.demo.model.Department;
import com.dayi.demo.model.Job;
import com.dayi.demo.model.User;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.DepartmentService;
import com.dayi.demo.service.JobService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.MD5Util;
import com.dayi.demo.util.Msg;
import com.dayi.demo.util.UUIDUtil;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户管理的控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 跳转用户管理主页
     * @return
     */
    @RequestMapping
    public String toUserManageJsp(){
        return "sys/userManage";
    }

    /**
     * 根据特定条件获取用户信息（不需要的条件置NULL）
     * @param name 姓名（模糊搜索）
     * @param phone 手机号码（模糊搜索）
     * @param no 工号（模糊搜索）
     * @param limitStart 分页
     * @param limitEnd
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    @RequestMapping("/listUser")
    @ResponseBody
    public Msg listUser(String name, String phone, String no, Integer limitStart,
                        Integer limitEnd, String order, String sort){
        System.out.println(limitStart + "," + limitEnd);
        //对传入数据进行非空判断和处理
        if (StringUtils.isBlank(name)){
            name = null;
        }
        if (StringUtils.isBlank(phone)){
            phone = null;
        }
        if (StringUtils.isBlank(no)){
            no = null;
        }
        String orderByClause = null;
        if (StringUtils.isNotBlank(order)){
            orderByClause = order + " " + sort;
        }

        Map<String ,Object> map = userService.listUserTable(name, phone, no, limitStart, limitEnd, orderByClause);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 用户管理 - 增加新用户
     * @param name 姓名
     * @param no 工号
     * @param phone 手机号码
     * @param email 邮箱地址
     * @param entrydate 入职日期
     * @param directSupervisorNo 上司工号
     * @param sex 性别
     * @param jobId 岗位id
     * @return 添加成功返回100状态码，其他情况请查看对应的提示信息
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(String name, String no, String phone, String email, Date entrydate,
                       String directSupervisorNo, String sex, String jobId){
        //验证参数
        if (StringUtils.isBlank(name)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (StringUtils.isBlank(no)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (StringUtils.isBlank(phone) || 11 != phone.length()){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (StringUtils.isBlank(email)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (StringUtils.isBlank(sex)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (StringUtils.isBlank(jobId)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if (null == entrydate){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        //获取岗位信息
        Job job = jobService.getJob(jobId);
        if(null == job){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装数据
        User user = new User();
        user.setId(UUIDUtil.getUUID());
        user.setName(name);
        user.setNo(no);
        user.setPhone(phone);
        user.setEmail(email);
        user.setEntrydate(entrydate);
        user.setSex(sex);
        user.setJobId(job.getId());
        user.setJobName(job.getName());
        user.setDepartmentName(job.getDepartmentName());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setPassword(MD5Util.encode2hex("monda6")); //新增用户统一默认密码为monda6
        if(StringUtils.isNotBlank(directSupervisorNo)){
            //如果有直属上司
            user.setDirectSupervisorNo(directSupervisorNo);
        }
        //增加用户服务
        boolean isSuccess = userService.addUser(user);
        if (isSuccess){
            return Msg.success("增加新用户成功");
        }
        return Msg.error("增加新用户失败，请检查信息是否正确，或手机号码、工号重复");
    }

    /**
     * 用户管理 - 更新用户
     * @param id UUID
     * @param name 姓名
     * @param phone 手机号码
     * @param email 企业邮箱
     * @param entrydate 日职日期
     * @param directSupervisorNo 直属上司工号
     * @param sex 性别
     * @param jobId 岗位
     * @return 成功100，其余失败并携带信息
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Msg updateUser(String id, String name, String phone, String email, Date entrydate,
                          String directSupervisorNo, String sex, String jobId){
        //校验参数
        boolean validNotNull = (StringUtils.isBlank(id)) || (StringUtils.isBlank(name))
                || (StringUtils.isBlank(phone)) || (StringUtils.isBlank(email)) || null == entrydate
                || (StringUtils.isBlank(sex)) || (StringUtils.isBlank(jobId)) ;
        if (validNotNull){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //获取岗位信息
        Job job = jobService.getJob(jobId);
        if(null == job){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        //获取当前用户的数据库数据
        User oldUser = userService.getUserById(id);

        //封装数据(如果数据未发生变化，则不更新对应字段)
        User user = new User();
        user.setId(id);
        if (!oldUser.getName().equals(name)) {
            user.setName(name);
        }
        if (!oldUser.getPhone().equals(phone)) {
            user.setPhone(phone);
        }
        if (!oldUser.getEmail().equals(email)) {
            user.setEmail(email);
        }
        if (!oldUser.getEntrydate().equals(entrydate)) {
            user.setEntrydate(entrydate);
        }
        if (!oldUser.getSex().equals(sex)) {
            user.setSex(sex);
        }
        if (!oldUser.getJobId().equals(jobId)) {
            user.setJobId(job.getId());
            user.setJobName(job.getName());
            user.setDepartmentName(job.getDepartmentName());
        }
        if (StringUtils.isNotBlank(directSupervisorNo)){
            //如果有直属上司
            user.setDirectSupervisorNo(directSupervisorNo);
        } else {
            user.setDirectSupervisorNo("");
        }

        ResStatusEnum res = userService.updateUser(user);
        if (null == res) {
            return Msg.success("更新成功");
        }
        return Msg.res(res);
    }

    /**
     * 用户管理 - 删除一个用户
     * @param id UUID
     * @return 成功状态码为100，失败为200
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Msg deleteUser(String id){
        if (StringUtils.isBlank(id)){
            return Msg.error("传入参数有误");
        }
        try {
            userService.deleteUser(id, null, null);
            return Msg.success("删除成功");
        } catch (RuntimeException ex){
            return Msg.error(ex.getMessage());
        }
    }

    /**
     * 用户管理 - 获取用户
     * @param id UUID
     * @return 成功获取100，“user”
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public Msg getUserById(String id){
        if (StringUtils.isBlank(id)){
            return Msg.error("传入参数错误");
        }
        User user = userService.getUserById(id);
        return Msg.success(null).add("user", user);
    }

    /**
     * 用户管理 - 重置密码
     * @param id UUID
     * @param password 新密码
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Msg resetPassword(String id, String password){
        //参数校验
        if(StringUtils.isBlank(id)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }
        if(StringUtils.isBlank(password)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        ResStatusEnum res = userService.updateUser(user);
        if (null == res){
            return Msg.success("重置密码成功");
        }
        return Msg.res(res);
    }

    /**
     * 重置个人密码
     * @param session
     * @param password 新密码
     * @return
     */
    @RequestMapping("/resetMyPassword")
    @ResponseBody
    public Msg resetMyPassword(HttpSession session, String password){
        //参数校验
        if (StringUtils.isBlank(password)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        User user = (User) session.getAttribute("user");

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(password);
        newUser.setUpdatetime(new Date());

        ResStatusEnum res = userService.updateUser(newUser);
        if (null == res){
            return  Msg.success(null);
        }
        return Msg.res(res);
    }

    /**
     * 个人信息模态框中，显示个人信息，直属上司信息，部门总监信息
     * @param session
     * @return
     */
    @RequestMapping("/showMeInfo")
    @ResponseBody
    public Msg showMeInfo(HttpSession session){
        User myUser = (User) session.getAttribute("user");
        User bossUser = null;
        User majordomoUser = null;

        //更新一下当前的用户信息,并保存到session中
        myUser = userService.getUserById(myUser.getId());
        session.setAttribute("user", myUser);

        //获取直属上司信息
        if (StringUtils.isNotBlank(myUser.getDirectSupervisorNo())){
            bossUser = userService.getUserByNo(myUser.getDirectSupervisorNo());
        }

        //获取部门总监信息
        Department department = departmentService.getDepartmentByName(myUser.getDepartmentName());
        if (null != department && StringUtils.isNotBlank(department.getMajordomo())){
            majordomoUser = userService.getUserByNo(department.getMajordomo());
        }

        return Msg.success(null).add("me", myUser).add("boss", bossUser).add("majordomo", majordomoUser);
    }

    /**
     * 参数格式化，将前台的“yyyy-MM-dd”字符串自动转化为Date类
     * @param bin
     */
    @InitBinder("entrydate")
    public void initBinderDate(ServletRequestDataBinder bin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }
}
