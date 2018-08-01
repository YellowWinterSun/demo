package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.OfficialItemRes;
import com.dayi.demo.controller.model.TemplateRes;
import com.dayi.demo.dao.DimensionalityDao;
import com.dayi.demo.dao.OfficialEvaluationItemDao;
import com.dayi.demo.dao.OfficialPerformanceEvaluationDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.*;
import com.dayi.demo.model.modelEnum.OfficialEvaluationEnum;
import com.dayi.demo.service.ExamineService;
import com.dayi.demo.service.TemplateService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 绩效考核模块 - 进行本月绩效考核服务
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/26
 */
@Service
public class ExamineServiceImpl implements ExamineService {

    //绩效考核正表Dao
    @Autowired
    private OfficialPerformanceEvaluationDao officialPerformanceEvaluationDao;
    @Autowired
    private OfficialEvaluationItemDao officialEvaluationItemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DimensionalityDao dimensionalityDao;

    @Autowired
    private TemplateService templateService;

    /**
     * 检查当前用户，本月是否已经进行了绩效考核。
     *
     * @param userNo
     * @return 本月进行了true，没有进行false
     */
    @Override
    public boolean validUserExamine(String userNo) {
        if (StringUtils.isBlank(userNo)){
            return false;
        }

        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;

        int num = officialPerformanceEvaluationDao.countByUserYearMonth(userNo, year, month);
        if (num > 0){
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void doExamine(String userNo, String templateId, String items) throws RuntimeException{
        //获取考核人信息
        User user = userDao.getUserByNo(userNo);
        //获取考核模板信息
        TemplateRes templateRes = templateService.getTemplateResById(templateId);

        //获取当前时间
        Calendar cale = Calendar.getInstance();
        Integer year = cale.get(Calendar.YEAR);
        Integer month = cale.get(Calendar.MONTH) + 1;

        //生成一个绩效考核表
        OfficialPerformanceEvaluation officialEvaluation = new OfficialPerformanceEvaluation();
        officialEvaluation.setId(UUIDUtil.getUUID());
        officialEvaluation.setDepartmentName(user.getDepartmentName());
        officialEvaluation.setJobName(user.getJobName());
        officialEvaluation.setYear(year.shortValue());
        officialEvaluation.setMonth(month.byteValue());
        officialEvaluation.setUserNo(user.getNo());
        officialEvaluation.setUserName(user.getName());
        officialEvaluation.setStatus(OfficialEvaluationEnum.INIT.getValue());

        officialPerformanceEvaluationDao.addOfficialEvaluation(officialEvaluation);

        //生成对应的正表绩效考核项目
        for (String itemStr : items.split(",")){
            //itemStr : id-selfContent-selfGrade
            String itemId = itemStr.split("-")[0];
            String itemSelfContent = itemStr.split("-")[1];
            String itemSelfGrade = itemStr.split("-")[2];

            //获取 模板考核项目
            EvaluationItem evaluationItem = templateService.getItemById(itemId);
            //获取 考核维度信息
            Dimensionality dimensionality = dimensionalityDao.getDimensionalityById(evaluationItem.getDimensionalityId());

            OfficialEvaluationItem officialItem = new OfficialEvaluationItem();
            officialItem.setId(UUIDUtil.getUUID());
            officialItem.setOfficialId(officialEvaluation.getId());
            officialItem.setDimensionalityName(dimensionality.getName());
            officialItem.setDimensionalityLevel(dimensionality.getLevel());
            officialItem.setName(evaluationItem.getName());
            officialItem.setWeight(evaluationItem.getWeight());
            officialItem.setContent(evaluationItem.getContent());
            officialItem.setSelfContent(itemSelfContent);
            officialItem.setSelfGrade(Byte.parseByte(itemSelfGrade));

            officialEvaluationItemDao.addOfficialItem(officialItem);
        }

        return;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void doBoss1Examine(String bossNo, String officialId, String items, String boss1remark) throws RuntimeException{
        //获取直属上司信息
        User user = userDao.getUserByNo(bossNo);

        //获取当前的绩效考核表
        OfficialPerformanceEvaluation officialEvaluation = officialPerformanceEvaluationDao.getById(officialId);

        //判断当前考核表的状态
        if (!officialEvaluation.getStatus().equals(OfficialEvaluationEnum.INIT.getValue())){
            //不处于直属上司工作流的状态
            throw new RuntimeException();
        }

        //更新上级签名和上级内容
        officialEvaluation.setBoss1No(user.getNo());
        officialEvaluation.setBoss1Name(user.getName());
        officialEvaluation.setBoss1Remark(boss1remark);
        //更新 updatetime
        officialEvaluation.setUpdatetime(new Date());
        //更新 考核状态
        officialEvaluation.setStatus(OfficialEvaluationEnum.BOSS1.getValue());

        //更新绩效考核表
        officialPerformanceEvaluationDao.updateOfficialEvaluation(officialEvaluation);

        //更新绩效考核项目的上级评分
        for (String itemStr : items.split(",")){
            //itemStr : id-boss1Grade
            String itemId = itemStr.split("-")[0];
            String bossGrade = itemStr.split("-")[1];

            //获取 考核项目
            OfficialEvaluationItem officialItem = officialEvaluationItemDao.getById(itemId);
            //更新内容
            officialItem.setBossGrade(Byte.parseByte(bossGrade));
            officialItem.setUpdatetime(new Date());

            officialEvaluationItemDao.updateOfficialItem(officialItem);
        }
        return;
    }

    @Override
    public List<OfficialPerformanceEvaluation> listOfficialEvaluationByUserNos(List<String> userNos, String status) {
        if (userNos == null || userNos.isEmpty()){
            return new ArrayList<>();
        }

        return officialPerformanceEvaluationDao.listByUserNos(userNos, status);
    }

    @Override
    public List<OfficialPerformanceEvaluation> listOfficialEvaluationByStatus(String status) {
        return officialPerformanceEvaluationDao.listByStatus(status);
    }

    @Override
    public boolean validUserIsOfficialEvaluationBoss1(String id, String userNo) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(userNo)){
            return false;
        }
        return officialPerformanceEvaluationDao.validUserIsOfficialEvaluationBoss1(id, userNo);
    }

    @Override
    public OfficialPerformanceEvaluation getOfficialEvaluationById(String id) {
        return officialPerformanceEvaluationDao.getById(id);
    }

    @Override
    public List<OfficialItemRes> listOfficialItemResByOfficialId(String officialEvaluationId) {
        //获取当前考核项目列表（按考核维度优先级降序排序）
        List<OfficialEvaluationItem> listItem = officialEvaluationItemDao.listOfficialItem(officialEvaluationId);

        //二次封装考核项目信息
        List<OfficialItemRes> listResult = new ArrayList<>();
        //记录考核维度个数
        Map<String, Integer> map = new HashMap<>();

        for (OfficialEvaluationItem item : listItem){
            OfficialItemRes res = new OfficialItemRes(item);

            if (!map.containsKey(item.getDimensionalityName())){
                //Map中不存在这个考核维度
                //设置当前考核项目为该考核维度的第一个
                res.setFirst(true);
                //记录个数
                Integer total = 1;
                map.put(item.getDimensionalityName(), total);
            } else {
                //Map中存在这个考核维度
                res.setFirst(false);

                //计数器+1
                Integer total = map.get(item.getDimensionalityName());
                map.put(item.getDimensionalityName(), ++total);
            }

            listResult.add(res);
        }

        //设置考核维度的项目数量
        for (OfficialItemRes res : listResult){
            if (res.isFirst()){
                Integer total = map.get(res.getDimensionalityName());
                res.setItemNum(total);
            }
        }

        return listResult;
    }

    @Override
    public boolean validUserIsOfficialEvaluationBoss2(String id, String bossNo) {
        //先获取绩效考核表的考核人工号
        OfficialPerformanceEvaluation evaluation = officialPerformanceEvaluationDao.getById(id);
        String userNo = evaluation.getUserNo();

        //验证考核人的部门负责人是否为bossNo
        return userDao.validBossIsUserDepartmentMajordomo(bossNo, userNo);
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void doBoss2Examine(String bossNo, String officialId, String boss2remark, Integer ex, String exContent) throws RuntimeException{
        //获取部门负责人信息
        User boss = userDao.getUserByNo(bossNo);

        //获取当前绩效考核表
        OfficialPerformanceEvaluation officialPerformanceEvaluation = officialPerformanceEvaluationDao.getById(officialId);

        //判断当前考核表的状态
        if (!officialPerformanceEvaluation.getStatus().equals(OfficialEvaluationEnum.BOSS1.getValue())){
            //不处于 部门总监工作流的状态
            throw new RuntimeException();
        }

        //更新
        officialPerformanceEvaluation.setBoss2No(boss.getNo());
        officialPerformanceEvaluation.setBoss2Name(boss.getName());
        officialPerformanceEvaluation.setBoss2Remark(boss2remark);
        officialPerformanceEvaluation.setExGrade(ex.byteValue());
        officialPerformanceEvaluation.setExReason(exContent);
        //更新考核状态
        officialPerformanceEvaluation.setStatus(OfficialEvaluationEnum.BOSS2.getValue());

        officialPerformanceEvaluation.setUpdatetime(new Date());

        officialPerformanceEvaluationDao.updateOfficialEvaluation(officialPerformanceEvaluation);

        return;
    }

    @Override
    public void doHrExamine(String hrNo, String officialId, boolean hrResult) throws RuntimeException{
        //获取HR信息
        User hr = userDao.getUserByNo(hrNo);

        //获取当前绩效考核表
        OfficialPerformanceEvaluation officialPerformanceEvaluation = officialPerformanceEvaluationDao.getById(officialId);

        //判断当前绩效考核表的考核状态
        if (!officialPerformanceEvaluation.getStatus().equals(OfficialEvaluationEnum.BOSS2.getValue())){
            //不处于 最终审核状态
            throw new RuntimeException();
        }

        List<OfficialEvaluationItem> list = officialEvaluationItemDao.listOfficialItem(officialId);
        //计算当前的自评总分
        int selfGrade = 0;
        //计算当前的直属上司评分
        int bossGrade = 0;
        for (OfficialEvaluationItem item : list){
            if (null != item.getSelfGrade()){
                selfGrade += item.getSelfGrade();
            }
            if (null != item.getBossGrade()){
                bossGrade += item.getBossGrade();
            }
        }
        //计算 自评分20% + 上司评分80%
        double resultGrade = selfGrade * 0.2 + bossGrade * 0.8;
        BigDecimal finalGrade = new BigDecimal(resultGrade);

        if (hrResult){
            //特别分通过
            int exGrade = officialPerformanceEvaluation.getExGrade();
            finalGrade = finalGrade.add(new BigDecimal(exGrade)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        System.out.println("finalGrade:" + finalGrade);

        //更新
        officialPerformanceEvaluation.setHrNo(hr.getNo());
        officialPerformanceEvaluation.setHrName(hr.getName());
        officialPerformanceEvaluation.setFinalGrade(finalGrade);

        officialPerformanceEvaluation.setStatus(OfficialEvaluationEnum.HR.getValue());

        officialPerformanceEvaluation.setUpdatetime(new Date());

        officialPerformanceEvaluationDao.updateOfficialEvaluation(officialPerformanceEvaluation);

        return;
    }

    @Override
    public boolean checkExamineing(String userNo) {
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();
        c.andUserNoEqualTo(userNo);
        //处于考核状态INIT,BOSS1,BOSS2
        c.andStatusNotEqualTo(OfficialEvaluationEnum.HR.getValue());

        if (officialPerformanceEvaluationDao.countByExample(example) > 0){
            return true;
        }
        return false;
    }
}
