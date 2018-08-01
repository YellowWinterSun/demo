package com.dayi.demo.controller;

import com.dayi.demo.controller.model.OfficialEvaluationRes;
import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.modelEnum.OfficialEvaluationEnum;
import com.dayi.demo.service.DepartmentService;
import com.dayi.demo.service.OfficialEvaluationService;
import com.dayi.demo.util.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 绩效考核模块 - 绩效考核汇总功能控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/29
 */
@Controller
@RequestMapping("/evaluation/viewExamine")
public class ViewExamineController {

    @Autowired
    private OfficialEvaluationService officialEvaluationService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 跳转主页
     * @return
     */
    @RequestMapping
    public String toIndex(){
        return "evaluation/viewExamine";
    }

    /**
     * 查看绩效考核表
     * @param year 筛选年份
     * @param month 筛选月份
     * @param userName 考核人姓名（模糊搜索）
     * @param userNo 考核人工号（模糊搜索）
     * @param status 考核状态
     * @param jobName 岗位名称
     * @param departmentName 部门名称
     * @param recurve 当筛选了“部门”时，这个参数生效，true表示包含子部门，false表示不包含子部门
     * @param limitStart 分页开始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Msg list(Integer year, Integer month, String userName, String userNo, OfficialEvaluationEnum status,
                    String jobName, String departmentName, Boolean recurve, Integer limitStart, Integer limitEnd,
                    String order, String sort){

        Map<String, Object> map = null;
        List<String> departmentNames = null;

        //是否传入了部门
        if (StringUtils.isNotBlank(departmentName)){

            departmentNames = new ArrayList<>();
            departmentNames.add(departmentName);
        }

        //验证是否需要包含子部门
        if (null == departmentNames){
            //不需要筛选部门
            map = officialEvaluationService.listOfficialEvaluationRes(year, month, userName, userNo,
                    null == status ? null : status.getValue(), jobName, null, limitStart, limitEnd,
                    order, sort);

        } else if (null == recurve || !recurve) {
            //不包含子部门
            map = officialEvaluationService.listOfficialEvaluationRes(year, month, userName, userNo,
                    null == status ? null : status.getValue(), jobName, departmentNames, limitStart, limitEnd,
                    order, sort);

        } else {
            //包含子部门
            //搜索所有子部门集合
            Department department = departmentService.getDepartmentByName(departmentName);
            List<Department> listDepartments = departmentService.listByParentDepartmentRecurve(department.getId());

            //记录所有子部门名称
            for (Department data : listDepartments){
                departmentNames.add(data.getName());
            }

            map = officialEvaluationService.listOfficialEvaluationRes(year, month, userName, userNo,
                    null == status ? null : status.getValue(), jobName, departmentNames, limitStart, limitEnd,
                    order, sort);
        }

        //获取返回结果集合
        List<OfficialEvaluationRes> listRes = (List<OfficialEvaluationRes>) map.get("list");
        //进行第三次封装，目的是为了迎合前端的数据展示（主部门，和分部门）
        if (StringUtils.isBlank(departmentName)){
            //没有传入部门进行搜索，因此将主部门设为当前部门
            for (OfficialEvaluationRes res : listRes){
                res.setMainDepartment(res.getDepartmentName());
                res.setDepartmentName("");
            }
        } else {
            for (OfficialEvaluationRes res : listRes){
                if (!departmentName.equals(res.getDepartmentName())) {
                    //要搜索的部门名称不等于当前部门名，则代表当前是分部门
                    res.setMainDepartment(departmentName);
                } else {
                    //当前部门是主部门
                    res.setMainDepartment(departmentName);
                    res.setDepartmentName("");
                }
            }
            //默认主部门放在顶部
            Collections.sort(listRes, (o1, o2) -> {
                //主部门放头部
                if (StringUtils.isBlank(o1.getDepartmentName()) && StringUtils.isNotBlank(o2.getDepartmentName())){
                    return -1;
                }
                if (StringUtils.isNotBlank(o1.getDepartmentName()) && StringUtils.isBlank(o2.getDepartmentName())){
                    return 1;
                }
                if (StringUtils.isBlank(o1.getDepartmentName()) && StringUtils.isBlank(o2.getDepartmentName())){
                    return 0;
                }

                if (o1.getDepartmentName().compareTo(o2.getDepartmentName()) > 0){
                    return 1;
                }
                else if (o1.getDepartmentName().compareTo(o2.getDepartmentName()) < 0){
                    return -1;
                }
                else {
                    return 0;
                }
            });
        }

        return Msg.success(null).add("list", listRes)
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));

    }

    /**
     * 查看绩效考核表
     * @param officialEvaluationId
     * @return
     */
    @RequestMapping("/showOfficialEvaluationTable")
    public ModelAndView showOfficialEvaluationTable(String officialEvaluationId){
        //获取绩效考核表
        OfficialPerformanceEvaluation officialEvaluation = officialEvaluationService.getOfficialEvaluationById(officialEvaluationId);
        //获取考核项目
        List<OfficialItemRes> officialItemRes = officialEvaluationService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }
}
