package com.dayi.demo.service;

import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.PerformanceEvaluationTemplate;

import java.util.List;

/**
 * 绩效考核模块 - 进行本月绩效考核服务
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/26
 */
public interface ExamineService {

    /**
     * 检查当前用户，本月是否已经进行了绩效考核。
     * @param userNo 用户工号
     * @return 本月进行了true，没有进行false
     */
    boolean validUserExamine(String userNo);

    /**
     * 考核人进行绩效考核，根据考核人填写的信息和考核模板，生成一个考核表
     * @param userNo 考核人工号
     * @param templateId 考核表模板id
     * @param items 考核项目组合体内容  项目id-自评内容-自评分，项目id-自评内容-自评分.......etc
     */
    void doExamine(String userNo, String templateId, String items);

    /**
     * 直属上司进行绩效考核操作，为考核人评分以及评价
     * @param bossNo 直属上司工号
     * @param officialId 绩效考核表id
     * @param items 考核项目组合体  项目id-上司评分，项目id-上司评分.....etc
     */
    void doBoss1Examine(String bossNo, String officialId, String items, String boss1remark);

    /**
     * 获取绩效考核表，根据用户工号集合
     * @param userNos 用户工号集合
     * @param status 考核状态（可选）
     * @return
     */
    List<OfficialPerformanceEvaluation> listOfficialEvaluationByUserNos(List<String> userNos, String status);

    /**
     * 根据考核状态，获取绩效考核表
     * @param status k考核状态
     * @return
     */
    List<OfficialPerformanceEvaluation> listOfficialEvaluationByStatus(String status);

    /**
     * 判断某用户，是否为某绩效考核表考核人的直属上司
     * @param id 绩效考核表id
     * @param userNo 某用户工号
     * @return
     */
    boolean validUserIsOfficialEvaluationBoss1(String id, String userNo);

    /**
     * 根据id，获取绩效考核表信息
     * @param id 绩效考核表id
     * @return
     */
    OfficialPerformanceEvaluation getOfficialEvaluationById(String id);

    /**
     * 根据绩效考核表id，获取其考核项目（按照考核维度优先级降序排序）
     * @param officialEvaluationId 绩效考核表id
     * @return
     */
    List<OfficialItemRes> listOfficialItemResByOfficialId(String officialEvaluationId);

    /**
     * 判断某用户，是否为某绩效考核表考核人的部门负责人
     * @param id 绩效考核表id
     * @param bossNo 待验证的用户工号
     * @return
     */
    boolean validUserIsOfficialEvaluationBoss2(String id, String bossNo);

    /**
     * 部门负责人对考核表进行考核
     * @param bossNo 部门负责人工号
     * @param officialId 考核表id
     * @param boss2remark 部门负责人评语
     * @param ex 特别分
     * @param exContent 特别分理由
     */
    void doBoss2Examine(String bossNo, String officialId, String boss2remark, Integer ex, String exContent);

    /**
     * 人力行政负责人对绩效考核表进行最终审核
     * @param hrNo 人力行政负责人工号
     * @param officialId 考核表id
     * @param hrResult 特别分审核结果
     */
    void doHrExamine(String hrNo, String officialId, boolean hrResult);

    /**
     * 查看用户是否有正在进行的绩效考核表
     * @param userNo 用户工号
     * @return true有，false无
     */
    boolean checkExamineing(String userNo);
}
