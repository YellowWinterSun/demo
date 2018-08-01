package com.dayi.demo.controller;

import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.controller.model.TemplateItemRes;
import com.dayi.demo.controller.model.TemplateRes;
import com.dayi.demo.model.Department;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.PerformanceEvaluationTemplate;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.OfficialEvaluationEnum;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.*;
import com.dayi.demo.util.Msg;
import org.apache.commons.lang3.StringUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 绩效考核模块 - 进行绩效考核功能 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/26
 */
@Controller
@RequestMapping("/evaluation/examine")
public class ExamineController {

    @Autowired
    private ExamineService examineService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MessageService messageService;

    /**
     * 跳转至 主页。并携带当前登录用户，当前可进行的绩效考核表信息
     * @param session
     * @return
     */
    @RequestMapping
    public ModelAndView toIndex(HttpSession session){

        ModelAndView mav = new ModelAndView("evaluation/examine");

        //判断是否是月底（默认25号以后为月底）
//        Calendar cale = Calendar.getInstance();
//        int day = cale.get(Calendar.DATE);
//        System.out.println(day);
//        if (day <= 25){
//            mav.addObject("msg", "当前不是月底，无法进行绩效考核!");
//            mav.addObject("doIt", true);
//            return mav;
//        }

        User user = (User)session.getAttribute("user");
        String userNo = user.getNo();

        //判断本月是否进行了考核
        boolean isDoIt = examineService.validUserExamine(userNo);
        if (isDoIt){
            //本月已经进行过考核。
            mav.addObject("doIt", true);
            mav.addObject("msg", "你本月已进行过绩效考核!");
            return mav;
        }
        //加载当前用户可进行的考核表
        List<PerformanceEvaluationTemplate> list = templateService.listTemplateByJobId(user.getJobId());
        mav.addObject("doIt", false);
        mav.addObject("list", list);

        return mav;
    }

    /**
     * 跳转 待处理的绩效考核表.jsp
     * @param session
     * @return
     */
    @RequestMapping("/pendingExamine")
    public ModelAndView toPendingExamineJsp(HttpSession session){

        User user = (User) session.getAttribute("user");

        //返回的list，定义
        //定义直属上司要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult1 = null;
        //定义部门负责人要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult2 = null;
        //定义HR要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult3 = null;

        //(1)直属上司待处理的绩效考核表
        List<User> listChildUser = userService.listUserByDirectSupervisorNo(user.getNo());
        if (!listChildUser.isEmpty()){
            //有下属
            List<String> listChildUserNos = new ArrayList<>();
            for (User data : listChildUser){
                listChildUserNos.add(data.getNo());
            }
            //获取下属正在考核的绩效考核表
            listResult1 = examineService.listOfficialEvaluationByUserNos(listChildUserNos, OfficialEvaluationEnum.INIT.getValue());
        } else {
            listResult1 = new ArrayList<>();
        }

        //(2)部门负责人待处理的绩效考核表
        List<Department> listDepartment = departmentService.listByMajordomo(user.getNo());
        if (!listDepartment.isEmpty()){
            //说明当前用户是部门负责人
            List<String> listDepartmentNames = new ArrayList<>();
            for (Department data : listDepartment){
                listDepartmentNames.add(data.getName());
            }
            List<User> listDepartmentUser = userService.listUserByDepartments(listDepartmentNames);
            if (listDepartmentUser.isEmpty()){
                //当前部门没有员工
                listResult2 = new ArrayList<>();
            } else {
                //当前部门有员工
                List<String> listUserNos = new ArrayList<>();
                for (User data : listDepartmentUser){
                    listUserNos.add(data.getNo());
                }
                //获取部门负责人的所有待处理绩效考核表
                listResult2 = examineService.listOfficialEvaluationByUserNos(listUserNos, OfficialEvaluationEnum.BOSS1.getValue());
            }

        } else {
            listResult2 = new ArrayList<>();
        }

        //(3)HR待处理的绩效考核表
        //判断当前用户是否拥有这条权限（绩效考核表最终审核权）
        boolean urlValid = roleService.validUserUrl(user.getNo(), "/evaluation/examine/pendingExamine/hrExam");
        if (urlValid){
            //有权限
            listResult3 = examineService.listOfficialEvaluationByStatus(OfficialEvaluationEnum.BOSS2.getValue());
        } else {
            //无权限
            listResult3 = new ArrayList<>();
        }

        ModelAndView mav = new ModelAndView("evaluation/pendingExamine");
        mav.addObject("list1", listResult1);
        mav.addObject("list2", listResult2);
        mav.addObject("list3", listResult3);
        return mav;
    }

    /**
     * 进行考核，跳转考核表页面
     * @param templateId 要采用的绩效考核表模板id
     */
    @RequestMapping("/examineTableShow")
    public ModelAndView examinetableShow(String templateId, HttpSession session){
        //判断是否是月底（默认25号以后为月底）
//        Calendar cale = Calendar.getInstance();
//        int day = cale.get(Calendar.DATE);
//        System.out.println(day);
//        if (day <= 25){
//            mav.addObject("msg", "当前不是月底，无法进行绩效考核!");
//            mav.addObject("doIt", true);
//            return mav;
//        }

        //先判断本月是否进行过绩效考核
        User user = (User)session.getAttribute("user");
        String userNo = user.getNo();

        //判断本月是否进行了考核
        boolean isDoIt = examineService.validUserExamine(userNo);
        if (isDoIt){
            //本月已经进行过考核。
            ModelAndView mav = new ModelAndView("evaluation/examineTableShow");
            mav.addObject("doIt", true);
            mav.addObject("msg", "你本月已进行过绩效考核!");
            return mav;
        }

        //获取考核模板信息
        TemplateRes templateRes = templateService.getTemplateResById(templateId);
        //验证绩效考核表模板是否正常（权重是否为100）
        if (templateRes.getWeight() != 100){
            System.out.println("考核模板权重分:" + templateRes.getWeight());
            ModelAndView mav = new ModelAndView("evaluation/examineTableShow");
            mav.addObject("doIt", true);
            mav.addObject("msg", templateRes.getName() + " 绩效考核表异常，请联系后台管理员查看考核表模板状态!");
            return mav;
        }

        templateRes.setUserName(user.getName());
        //获取考核项目信息
        List<TemplateItemRes> templateItemRes = templateService.getTemplateItemResById(templateId);

        //获取当前时间
        Calendar cale = Calendar.getInstance();
        Integer year = cale.get(Calendar.YEAR);
        Integer month = cale.get(Calendar.MONTH) + 1;


        ModelAndView mav = new ModelAndView("evaluation/examineTableShow");
        mav.addObject("doIt", false);
        mav.addObject("template", templateRes);
        mav.addObject("items", templateItemRes);
        mav.addObject("year", year);
        mav.addObject("month", month);

        return mav;
    }

    /**
     * 考核人提交绩效考核表
     * @param session
     * @param templateId 考核表所对应的模板
     * @param items 考核项目内容和自评分
     * @return
     */
    @RequestMapping("/submitExamine")
    @ResponseBody
    public Msg submitExamine(HttpSession session, String templateId, String items){
        //校验参数
        if (StringUtils.isBlank(templateId) || StringUtils.isBlank(items)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //先判断本月是否进行过绩效考核
        User user = (User)session.getAttribute("user");
        String userNo = user.getNo();

        //判断本月是否进行了考核
        boolean isDoIt = examineService.validUserExamine(userNo);
        if (isDoIt){
            //本月已经进行过考核。
            return Msg.error("提交失败，你本月已进行过绩效考核");
        }

        //查看考核模板是否匹配当前用户的岗位
        TemplateRes templateRes = templateService.getTemplateResById(templateId);
        if (!user.getJobId().equals(templateRes.getJobId())){
            return Msg.error("提交失败，你正在进行考核不匹配你的岗位");
        }

        //以上校验全部通过，下面进行绩效考核表的生成
        try {
            examineService.doExamine(userNo, templateId, items);
        } catch (RuntimeException e){
            e.printStackTrace();
            return Msg.error("提交失败，请联系管理员");
        }
        return Msg.success("提交成功");
    }

    /**
     * 直属上司对考核人进行考核
     * @param officialEvaluationId 绩效考核表id
     * @param session 直属上司session
     * @return
     */
    @RequestMapping("/pendingExamine/examineBoss1TableShow")
    public ModelAndView examineBoss1TableShow(String officialEvaluationId, HttpSession session){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId)){
            ModelAndView mav = new ModelAndView("evaluation/examineBoss1TableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "绩效考核表打开异常!");
            return mav;
        }

        //（1）先判断当前用户是否是该考核人的直属上司
        User user = (User) session.getAttribute("user");
        boolean valid = examineService.validUserIsOfficialEvaluationBoss1(officialEvaluationId, user.getNo());
        if (!valid){
            //非直属上司恶意操作
            ModelAndView mav = new ModelAndView("evaluation/examineBoss1TableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "这不是你的下属，你无权考核人家！如果是误操，请保持现有状态联系管理员报告BUG!");
            return mav;
        }

        //（2）获取绩效考核正表信息
        OfficialPerformanceEvaluation officialEvaluation = examineService.getOfficialEvaluationById(officialEvaluationId);

        //（3）获取正表考核项目信息
        List<OfficialItemRes> officialItemRes = examineService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/examineBoss1TableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }

    /**
     * 直属上司提交绩效考核表
     * @param session
     * @param officialEvaluationId 考核表id
     * @param items 考核项目上司评分
     * @param boss1remark 上级评价
     * @return
     */
    @RequestMapping("/pendingExamine/boss1SubmitExamine")
    @ResponseBody
    public Msg boss1SubmitExamine(HttpSession session, String officialEvaluationId, String items, String boss1remark){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId) || StringUtils.isBlank(items)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //判断当前用户是否为该表的直属上司
        User user = (User) session.getAttribute("user");
        boolean valid = examineService.validUserIsOfficialEvaluationBoss1(officialEvaluationId, user.getNo());
        if (!valid){
            //非直属上司恶意操作
            return Msg.error("你不是本考核人的直属上司。如果是误操，请保留现有状态，联系开发者汇报BUG");
        }

        //校验通过后，进行绩效考核表的更新
        try {
            examineService.doBoss1Examine(user.getNo(), officialEvaluationId, items, boss1remark);
        } catch (RuntimeException e){
            e.printStackTrace();
            return Msg.error("直属上司审核操作失败，请联系管理员");
        }
        return Msg.success("审核成功");

    }

    /**
     * 部门负责人对考核人进行考核
     * @param officialEvaluationId
     * @param session
     * @return
     */
    @RequestMapping("/pendingExamine/examineBoss2TableShow")
    public ModelAndView examineBoss2TableShow(String officialEvaluationId, HttpSession session){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId)){
            ModelAndView mav = new ModelAndView("evaluation/examineBoss2TableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "绩效考核表打开异常!");
            return mav;
        }

        // (1)先判断当前用户是否为考核表的部门负责人
        User user = (User) session.getAttribute("user");
        boolean valid = examineService.validUserIsOfficialEvaluationBoss2(officialEvaluationId, user.getNo());
        if (!valid){
            //非部门负责人
            ModelAndView mav = new ModelAndView("evaluation/examineBoss2TableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "你不是考核人的部门负责人！如果是误操，请保持现有状态联系管理员报告BUG!");
            return mav;
        }

        //(2)获取绩效考核正表信息
        OfficialPerformanceEvaluation officialEvaluation = examineService.getOfficialEvaluationById(officialEvaluationId);

        //(3)获取正表考核项目
        List<OfficialItemRes> officialItemRes = examineService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/examineBoss2TableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }

    /**
     * 部门负责人提交绩效考核表
     * @param session
     * @param officialEvaluationId 考核表id
     * @param boss2remark 考核项目部门负责人评语
     * @param ex 特别分
     * @param exContent 特别分理由
     * @return
     */
    @RequestMapping("/pendingExamine/boss2SubmitExamine")
    @ResponseBody
    public Msg boss2SubmitExamine(HttpSession session, String officialEvaluationId, String boss2remark,
                                   Integer ex, String exContent){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId) || null == ex){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //判断当前用户是否为考核人的部门负责人
        User user = (User) session.getAttribute("user");
        boolean valid = examineService.validUserIsOfficialEvaluationBoss2(officialEvaluationId, user.getNo());
        if (!valid){
            //非部门负责人
            return Msg.error("你不是考核人的部门负责人，如果是误操，请保留现有状态，联系开发者汇报BUG");
        }

        //校验通过后，进行绩效考核表的更新
        try {
            examineService.doBoss2Examine(user.getNo(), officialEvaluationId, boss2remark, ex, exContent);
        } catch (RuntimeException e){
            e.printStackTrace();
            return Msg.error("部门负责人审核操作异常，请联系管理员");
        }
        //通知考核人查看绩效考核表
        messageService.informOfficialEvaluationUser(officialEvaluationId);

        return Msg.success(null);

    }

    /**
     * 人力行政负责人对绩效考核表进行最终审核
     * @param officialEvaluationId
     * @param session
     * @return
     */
    @RequestMapping("/pendingExamine/examineHrTableShow")
    public ModelAndView examineHrTableShow(String officialEvaluationId, HttpSession session){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId)){
            ModelAndView mav = new ModelAndView("evaluation/examineHrTableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "绩效考核表打开异常!");
            return mav;
        }

        User user = (User) session.getAttribute("user");

        //（1）先判断当前用户是否有 审核考核表的最终权限
        //判断当前用户是否拥有这条权限（绩效考核表最终审核权）
        boolean urlValid = roleService.validUserUrl(user.getNo(), "/evaluation/examine/pendingExamine/hrExam");
        if (!urlValid){
            //无权限
            ModelAndView mav = new ModelAndView("evaluation/examineHrTableShow");
            mav.addObject("doIt", false);
            mav.addObject("msg", "你无权操作这个绩效考核表!");
            return mav;
        }

        //(2)获取绩效考核正表信息
        OfficialPerformanceEvaluation officialEvaluation = examineService.getOfficialEvaluationById(officialEvaluationId);

        //(3)获取正表考核项目
        List<OfficialItemRes> officialItemRes = examineService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/examineHrTableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }

    /**
     * 人力行政负责人最终审核绩效考核表
     * @param session
     * @param officialEvaluationId 绩效考核表id
     * @param hrResult 特别分审核结果
     * @return
     */
    @RequestMapping("/pendingExamine/hrSubmitExamine")
    @ResponseBody
    public Msg hrSubmitExamine(HttpSession session, String officialEvaluationId, Boolean hrResult){
        //校验参数
        if (StringUtils.isBlank(officialEvaluationId) || null == hrResult){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        User user = (User) session.getAttribute("user");

        //判断当前用户是否拥有绩效考核表最终审核权
        boolean urlValid = roleService.validUserUrl(user.getNo(), "/evaluation/examine/pendingExamine/hrExam");
        if (!urlValid){
            //无权限
            return Msg.error("你无权进行此操作");
        }

        //校验通过后，进行绩效考核表的更新
        try {
            examineService.doHrExamine(user.getNo(), officialEvaluationId, hrResult);
        } catch (RuntimeException e){
            e.printStackTrace();
            return Msg.error("人力行政部最终审核操作异常，请联系管理员");
        }
        return Msg.success(null);
    }

    /**
     * 展示绩效考核表（需要权限）（允许展示任何一张绩效考核表）
     * @param officialEvaluationId
     * @param session
     * @return
     */
    @RequestMapping("/officialEvaluationTableShow")
    public ModelAndView examineHrTableShow(String officialEvaluationId){
        //(1)获取绩效考核正表信息
        OfficialPerformanceEvaluation officialEvaluation = examineService.getOfficialEvaluationById(officialEvaluationId);

        //(2)获取正表考核项目
        List<OfficialItemRes> officialItemRes = examineService.listOfficialItemResByOfficialId(officialEvaluationId);

        ModelAndView mav = new ModelAndView("evaluation/officialEvaluationTableShow");
        mav.addObject("doIt", true);
        mav.addObject("official", officialEvaluation);
        mav.addObject("items", officialItemRes);

        return mav;
    }

    /**
     * (header.jsp中通知功能)查看待处理的绩效考核表条数
     * @param session
     * @return
     */
    @RequestMapping("/alertPendingExamine")
    @ResponseBody
    public Msg alertPendingExamine(HttpSession session){

        User user = (User) session.getAttribute("user");

        //返回的list，定义
        //定义直属上司要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult1 = null;
        //定义部门负责人要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult2 = null;
        //定义HR要处理的绩效考核表集合
        List<OfficialPerformanceEvaluation> listResult3 = null;

        //(1)直属上司待处理的绩效考核表
        List<User> listChildUser = userService.listUserByDirectSupervisorNo(user.getNo());
        if (!listChildUser.isEmpty()){
            //有下属
            List<String> listChildUserNos = new ArrayList<>();
            for (User data : listChildUser){
                listChildUserNos.add(data.getNo());
            }
            //获取下属正在考核的绩效考核表
            listResult1 = examineService.listOfficialEvaluationByUserNos(listChildUserNos, OfficialEvaluationEnum.INIT.getValue());
        } else {
            listResult1 = new ArrayList<>();
        }

        //(2)部门负责人待处理的绩效考核表
        List<Department> listDepartment = departmentService.listByMajordomo(user.getNo());
        if (!listDepartment.isEmpty()){
            //说明当前用户是部门负责人
            List<String> listDepartmentNames = new ArrayList<>();
            for (Department data : listDepartment){
                listDepartmentNames.add(data.getName());
            }
            List<User> listDepartmentUser = userService.listUserByDepartments(listDepartmentNames);
            if (listDepartmentUser.isEmpty()){
                //当前部门没有员工
                listResult2 = new ArrayList<>();
            } else {
                //当前部门有员工
                List<String> listUserNos = new ArrayList<>();
                for (User data : listDepartmentUser){
                    listUserNos.add(data.getNo());
                }
                //获取部门负责人的所有待处理绩效考核表
                listResult2 = examineService.listOfficialEvaluationByUserNos(listUserNos, OfficialEvaluationEnum.BOSS1.getValue());
            }

        } else {
            listResult2 = new ArrayList<>();
        }

        //(3)HR待处理的绩效考核表
        //判断当前用户是否拥有这条权限（绩效考核表最终审核权）
        boolean urlValid = roleService.validUserUrl(user.getNo(), "/evaluation/examine/pendingExamine/hrExam");
        if (urlValid){
            //有权限
            listResult3 = examineService.listOfficialEvaluationByStatus(OfficialEvaluationEnum.BOSS2.getValue());
        } else {
            //无权限
            listResult3 = new ArrayList<>();
        }

        listResult1.addAll(listResult2);
        listResult1.addAll(listResult3);

        return Msg.success(null).add("list", listResult1).add("size", listResult1.size());
    }

    /**
     * (header.jsp中通知功能)查看本月绩效考核表是否可进行
     * @param session
     * @return
     */
    @RequestMapping("/alertCanExamine")
    @ResponseBody
    public Msg alertCanExamine(HttpSession session){

        //判断是否是月底（默认25号以后为月底）
//        Calendar cale = Calendar.getInstance();
//        int day = cale.get(Calendar.DATE);
//        System.out.println(day);
//        if (day <= 25){
//            mav.addObject("msg", "当前不是月底，无法进行绩效考核!");
//            mav.addObject("doIt", true);
//            return mav;
//        }

        User user = (User)session.getAttribute("user");
        String userNo = user.getNo();

        //判断本月是否进行了考核
        boolean isDoIt = examineService.validUserExamine(userNo);
        if (isDoIt){
            //本月已经进行过考核。
            return Msg.error(null);
        }
        return Msg.success(null);

    }

    /**
     * （header.jsp中通知功能）查看是否有正在进行考核的绩效考核表
     * @param session
     * @return
     */
    @RequestMapping("/alertExamineing")
    @ResponseBody
    public Msg alertExamineing(HttpSession session){
        User user = (User) session.getAttribute("user");
        boolean result = examineService.checkExamineing(user.getNo());
        if (result){
            return Msg.success(null);
        }
        return Msg.error(null);
    }
}
