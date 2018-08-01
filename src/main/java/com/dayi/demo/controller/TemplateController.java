package com.dayi.demo.controller;

import com.dayi.demo.controller.model.EvaluationItemRes;
import com.dayi.demo.controller.model.TemplateItemRes;
import com.dayi.demo.controller.model.TemplateRes;
import com.dayi.demo.model.Dimensionality;
import com.dayi.demo.model.EvaluationItem;
import com.dayi.demo.model.PerformanceEvaluationTemplate;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.TemplateService;
import com.dayi.demo.util.Msg;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 绩效考核表模板管理 - 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Controller
@RequestMapping("/evaluation/templateManage")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 跳转绩效考核表模板管理的主页
     * @return
     */
    @RequestMapping
    public String toIndexJsp(){
        return "evaluation/templateManage";
    }

    /**
     * 根据特定条件获取绩效考核模板信息（不需要条件置为NULL）
     * @param name 模板名（模糊搜索）
     * @param jobId 岗位ID
     * @param limitStart 分页起始
     * @param limitEnd 分页记录树
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    @RequestMapping("/listTemplate")
    @ResponseBody
    public Msg listTemplate(String name, String jobId, Integer limitStart, Integer limitEnd,
                            String order, String sort){
        //校验参数
        if (StringUtils.isBlank(name)){
            name = null;
        }
        if (StringUtils.isBlank(jobId)){
            jobId = null;
        }
        String orderByClause = null;
        if (StringUtils.isNotBlank(order)){
            orderByClause = order + " " + sort;
        }

        Map<String, Object> map = templateService.listTemplate(name, jobId, limitStart, limitEnd, orderByClause);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 根据特定条件获取考核项目信息（不需要条件置为null）。默认以考核维度排序
     * @param name 考核项目名（模糊搜索)
     * @param dimensionalityId 考核维度id
     * @return
     */
    @RequestMapping("/listItem")
    @ResponseBody
    public Msg listItem(String name, String dimensionalityId){
        if (StringUtils.isBlank(name)){
            name = null;
        }
        if (StringUtils.isBlank(dimensionalityId)){
            dimensionalityId = null;
        }

        List<EvaluationItemRes> list = templateService.listEvaluationItem(name, dimensionalityId);

        return Msg.success(null).add("list", list);
    }

    /**
     * 获取所有考核维度，一般用作获取选项按钮用
     * @return
     */
    @RequestMapping("/listDimensionalityOption")
    @ResponseBody
    public Msg listDimensionalityOption(){
        List<Dimensionality> list = templateService.listDimensionality();
        return Msg.success(null).add("list", list);
    }

    /**
     * 根据id，获取考核项目对象信息
     * @param id 考核项目ID
     * @return
     */
    @RequestMapping("/getItemById")
    @ResponseBody
    public Msg getItemById(String id){
        if (StringUtils.isBlank(id)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        EvaluationItem item = templateService.getItemById(id);
        return Msg.success(null).add("item", item);
    }

    /**
     * 删除 模板-考核项目
     * @param id 考核项目id
     * @return
     */
    @RequestMapping("/deleteItem")
    @ResponseBody
    public Msg deleteItem(String id){
        if (StringUtils.isBlank(id)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        boolean result = templateService.deleteItem(id);
        if (result){
            return Msg.success(null);
        }
        return Msg.error("删除失败");
    }

    /**
     * 更新 考核项目信息
     * @param id 要更新的考核项目id
     * @param name 考核项目名称
     * @param weight 权重
     * @param content 内容
     * @param dimensionalityId 考核维度ID
     * @return
     */
    @RequestMapping("/updateItem")
    @ResponseBody
    public Msg updateItem(String id, String name, Integer weight, String content, String dimensionalityId){
        //校验参数
        if (StringUtils.isBlank(id) || StringUtils.isBlank(name)
                || StringUtils.isBlank(content) || StringUtils.isBlank(dimensionalityId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        EvaluationItem item = new EvaluationItem();
        item.setId(id);
        item.setName(name);
        item.setWeight((byte) weight.intValue());
        item.setContent(content);
        item.setDimensionalityId(dimensionalityId);
        item.setUpdatetime(new Date());

        templateService.updateItem(item);
        return Msg.success("更新成功");
    }

    /**
     * 考核模板管理 - 增加一个新的考核项目
     * @param name 考核项目名
     * @param weight 权重
     * @param content 定义及考核标准
     * @param dimensionalityId 考核维度ID
     * @return
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Msg addItem(String name, Integer weight, String content, String dimensionalityId){
        //校验参数
        if (StringUtils.isBlank(name) || null == weight || StringUtils.isBlank(content)
                || StringUtils.isBlank(dimensionalityId)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装
        EvaluationItem item = new EvaluationItem();
        item.setId(UUIDUtil.getUUID());
        item.setName(name);
        item.setWeight((byte)weight.intValue());
        item.setContent(content);
        item.setDimensionalityId(dimensionalityId);

        boolean result = templateService.addItem(item);
        if (result){
            return Msg.success(null);
        }
        return Msg.error("增加失败");
    }

    /**
     * 增加绩效考核表模板
     * @param name 绩效考核模板名
     * @param jobId 对应的岗位id
     * @param itemIds 绩效考核项目ID集合，用"-"隔开
     * @return
     */
    @RequestMapping("/addTemplate")
    @ResponseBody
    public Msg addTemplate(String name, String jobId, String itemIds){
        //校验参数
        if (StringUtils.isBlank(name) || StringUtils.isBlank(jobId)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //解析itemIds
        List<String> listItemIds = null;
        if (StringUtils.isNotBlank(itemIds)){
            listItemIds = new ArrayList<>();

            for (String itemId : itemIds.split("-")){
                listItemIds.add(itemId.trim());
            }
        }

        PerformanceEvaluationTemplate template = new PerformanceEvaluationTemplate();
        template.setName(name.trim());
        template.setJobId(jobId.trim());

        boolean result = templateService.addTemplate(template, listItemIds);
        if (result){
            return Msg.success(null);
        }
        return Msg.error("新增失败");
    }

    /**
     * 更新绩效考核模板
     * @param id 要更新的模板id
     * @param name 新名称
     * @param jobId 新对应的岗位id
     * @param itemIds 新的考核项目，以"-"隔开
     * @return
     */
    @RequestMapping("/updateTemplate")
    @ResponseBody
    public Msg updateTemplate(String id, String name, String jobId, String itemIds){
        //校验参数
        if (StringUtils.isBlank(id) || StringUtils.isBlank(name) || StringUtils.isBlank(jobId)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        //封装要更新的数据
        PerformanceEvaluationTemplate recordSelective = new PerformanceEvaluationTemplate();
        recordSelective.setId(id);
        recordSelective.setName(name);
        recordSelective.setJobId(jobId);
        recordSelective.setUpdatetime(new Date());

        //解析itemIds
        List<String> listItemIds = null;
        if (StringUtils.isNotBlank(itemIds)){
            listItemIds = new ArrayList<>();
            for (String itemId : itemIds.split("-")){
                listItemIds.add(itemId);
            }
        }

        try {
            templateService.updateTemplate(recordSelective, listItemIds);
        } catch (RuntimeException e){
            return Msg.error("更新失败");
        }

        return Msg.success(null);
    }

    /**
     * 删除绩效考核模板
     * @param id
     * @return
     */
    @RequestMapping("/deleteTemplate")
    @ResponseBody
    public Msg deleteTemplate(String id){
        //校验参数
        if (StringUtils.isBlank(id)){
            return Msg.res(ResStatusEnum.USER_FORM_INCOMPLETE);
        }

        try {
            templateService.deleteTemplate(id);
        } catch (RuntimeException e){
            return Msg.error(null);
        }
        return Msg.success(null);
    }

    /**
     * 绩效考核模板 - 展示模板
     * @param id 绩效考核模板id
     * @return
     */
    @RequestMapping("/templateShow")
    public ModelAndView templateShow(String id){

        //获取考核模板信息
        TemplateRes templateRes = templateService.getTemplateResById(id);
        //获取考核项目信息
        List<TemplateItemRes> templateItemRes = templateService.getTemplateItemResById(id);

        ModelAndView mav = new ModelAndView("evaluation/templateTableShow");
        mav.addObject("template", templateRes);
        mav.addObject("items", templateItemRes);

        return mav;
    }
}
