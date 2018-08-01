package com.dayi.demo.controller;

import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.OfficialEvaluationEnum;
import com.dayi.demo.service.OfficialEvaluationService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 查看本人绩效考核 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/28
 */
@Controller
@RequestMapping("/evaluation/viewMyExamine")
public class ViewMyExamineController {

    @Autowired
    private OfficialEvaluationService officialEvaluationService;
    @Autowired
    private UserService userService;

    /**
     * 跳转 查看本人绩效考核情况 首页
     * @return
     */
    @RequestMapping
    public String toIndex(){
        return "evaluation/viewMyExamine";
    }

    /**
     * 跳转 查看下属绩效考核情况 首页
     * @return
     */
    @RequestMapping("/viewMyChildExamine")
    public String toViewMyChildExamineJsp(){
        return "evaluation/viewMyChildExamine";
    }

    /**
     * 获取本人的绩效考核表情况
     * @param session
     * @param status 考核状态
     * @param limitStart
     * @param limitEnd
     * @param order
     * @param sort
     * @return
     */
    @RequestMapping("/listMyOfficialEvaluation")
    @ResponseBody
    public Msg listMyOfficialEvaluation(HttpSession session, OfficialEvaluationEnum status,
                                      Integer limitStart, Integer limitEnd, String order, String sort){
        User user = (User) session.getAttribute("user");

        Map<String, Object> map = officialEvaluationService.listOfficialEvaluation(user.getNo(),
                null == status ? null : status.getValue(), limitStart,
                limitEnd, order, sort);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 展示我的绩效考核表（只允许展示自己的考核表）
     * @return
     */
    @RequestMapping("/showMyOfficialEvaluationTable/{id}")
    public ModelAndView showOfficialEvaluationTable(HttpSession session, @PathVariable("id") String officialEvaluationId){
        User user = (User) session.getAttribute("user");

        //(1)获取绩效考核正表
        OfficialPerformanceEvaluation officialEvaluation = officialEvaluationService.getOfficialEvaluationById(officialEvaluationId);

        //判断绩效考核表是否为本人
        if (!user.getNo().equals(officialEvaluation.getUserNo())){
            //不是本人，无法查看
            ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "你无法查看这个绩效考核表");
            return mav;
        }

        //(2)获取正表考核项目
        List<OfficialItemRes> officialItemRes = officialEvaluationService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }

    /**
     * 查看自己的下属，绩效考核表情况（直属上司和部门总监和人力行政共用）
     * 返回所有现任下属，以及自己之前处理过绩效考核表
     * @param session
     * @param year 年份搜索
     * @param month 月份搜索
     * @param userName 考核人姓名（模糊搜索）
     * @param userNo 考核人工号（模糊搜索）
     * @return
     */
    @RequestMapping("/listMyChildOfficialEvaluation")
    @ResponseBody
    public Msg listMyChildOfficialEvaluation(HttpSession session, Integer year, Integer month
            , String userName, String userNo, Integer limitStart, Integer limitEnd, String order, String sort){

        User user = (User) session.getAttribute("user");

        // 获取。现在自己作为直属上司和部门负责人，所有下属的集合
        //(1)自己作为直属上司，获取所有下属的id集合
        List<User> list1 = userService.listUserByDirectSupervisorNo(user.getNo());

        //(2)自己作为部门负责人，获取部门所有人的id集合
        /*
        这个方法无法获取子部门的员工，如果后期需求有改变，则替换回这个方案
        List<User> list2 = userService.listUserByDepartmentMajordomo(user.getNo());
         */
        List<User> list2 = userService.listUserByDepartmentMajordomoRecurve(user.getNo());

        //组合list1和list2，去重复
        Set<String> setUserNos = new HashSet<>();
        //加入自己的工号，目的是去除自己，下属不包括自己
        setUserNos.add(user.getNo());
        for (User data : list1){
            setUserNos.add(data.getNo());
        }
        for (User data : list2){
            setUserNos.add(data.getNo());
        }
        //移除自己的工号，下属不包括自己
        setUserNos.remove(user.getNo());
        //Set转List
        List<String> listUserNos = new ArrayList<>(setUserNos);

        //查询出以前自己作为直属上司，部门负责人，人力行政负责人处理过的历史绩效考核表。（考虑到职位会改变，因此容易丢失以前的记录)
        Map<String, Object> map = officialEvaluationService.listMyChildOfficialEvaluation(listUserNos, user.getNo()
                , year, month, userName, userNo, limitStart, limitEnd, order, sort);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 展示下属的绩效考核表（只允许展示自己下属的考核表）
     * @return
     */
    @RequestMapping("/showMyChildOfficialEvaluationTable/{id}")
    public ModelAndView showMyChildOfficialEvaluationTable(HttpSession session, @PathVariable("id") String officialEvaluationId){
        User user = (User) session.getAttribute("user");

        //(1)获取绩效考核正表
        OfficialPerformanceEvaluation officialEvaluation = officialEvaluationService.getOfficialEvaluationById(officialEvaluationId);

        //判断可否查看当前绩效考核表
        //先判断是否为考核表的历史处理人
        boolean valid = false;
        if (user.getNo().equals(officialEvaluation.getBoss1No())){
            valid = true;
        }
        if (user.getNo().equals(officialEvaluation.getBoss2No())){
            valid = true;
        }
        if (user.getNo().equals(officialEvaluation.getHrNo())){
            valid = true;
        }

        if (valid){
            //是，则不需要再判断是不是下属了
            //(2)获取正表考核项目
            List<OfficialItemRes> officialItemRes = officialEvaluationService.listOfficialItemResByOfficialId(officialEvaluationId);

            ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
            mav.addObject("doIt", true);
            mav.addObject("official", officialEvaluation);
            mav.addObject("items", officialItemRes);

            return mav;
        }
        //不是考核表的处理人，则判断是否为下属
        valid = userService.validBossIsUserBoss(user.getNo(), officialEvaluation.getUserNo());

        if (!valid){
            //不是其上司
            ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "你无法查看这个绩效考核表");
            return mav;
        }

        //(2)获取正表考核项目
        List<OfficialItemRes> officialItemRes = officialEvaluationService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }
}
