package com.dayi.demo.dao;

import com.dayi.demo.mapper.OfficialPerformanceEvaluationMapper;
import com.dayi.demo.mapper.sql.OfficialPerformanceEvaluationMapperSql;
import com.dayi.demo.model.OfficialPerformanceEvaluation;
import com.dayi.demo.model.OfficialPerformanceEvaluationExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 绩效考核正表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/20
 */
@Repository
public class OfficialPerformanceEvaluationDao {
    @Autowired
    private OfficialPerformanceEvaluationMapper mapper;

    @Autowired
    private OfficialPerformanceEvaluationMapperSql mapperSql;

    /**
     * 根据考核人工号，修改考核人的签名（全表修改）
     * @param newUserName 新考核人签名
     * @param no 要修改的考核人工号
     * @return
     */
    public int updateUserNameByNo(String newUserName, String no){
        return mapperSql.updateUserNameByNo(newUserName, no);
    }

    /**
     * 根据直属上司的工号，修改直属上司的签名（全表修改）
     * @param newUserName 新签名
     * @param no 要修改的直属上司工号
     * @return
     */
    public int updateBoss1NameByNo(String newUserName, String no){
        return mapperSql.updateBoss1NameByNo(newUserName, no);
    }

    /**
     * 根据部门负责人工号，修改部门负责人的签名（全表修改）
     * @param newUserName 新签名
     * @param no 要修改的部门负责人工号
     * @return
     */
    public int updateBoss2NameByNo(String newUserName, String no){
        return mapperSql.updateBoss2NameByNo(newUserName, no);
    }

    /**
     * 根据人事部负责人工号，修改其签名（全表修改）
     * @param newUserName 新签名
     * @param no 要修改的人事部负责人工号
     * @return
     */
    public int updateHrNameByNo(String newUserName, String no){
        return mapperSql.updateHrNameByNo(newUserName, no);
    }

    /**
     * 统计一个用户，在某一个年月进行的绩效考核次数
     * @param userNo
     * @param year
     * @param month
     * @return
     */
    public int countByUserYearMonth(String userNo, Integer year, Integer month){
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();
        c.andUserNoEqualTo(userNo);
        c.andYearEqualTo(year.shortValue());
        c.andMonthEqualTo(month.byteValue());

        return mapper.countByExample(example);
    }

    /**
     * 统计符合条件的记录数
     * @param example
     * @return
     */
    public int countByExample(OfficialPerformanceEvaluationExample example){
        return mapper.countByExample(example);
    }

    /**
     * 更新绩效考核表
     * @param selective
     * @return
     */
    public int updateOfficialEvaluation(OfficialPerformanceEvaluation selective){
        return mapper.updateByPrimaryKeySelective(selective);
    }

    /**
     * 新增一个绩效考核正表
     * @param officialEvaluationSelective
     * @return
     */
    public int addOfficialEvaluation(OfficialPerformanceEvaluation officialEvaluationSelective){
        return mapper.insertSelective(officialEvaluationSelective);
    }

    /**
     * 根据用户工号集合，获取绩效考核表
     * @param userNos 用户工号集合
     * @param status 考核状态（可选）
     * @return
     */
    public List<OfficialPerformanceEvaluation> listByUserNos(List<String> userNos, String status){
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();
        c.andUserNoIn(userNos);

        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }

        return mapper.selectByExample(example);
    }

    /**
     * 根据工号，使用or条件，查询以前自己作为直属上司，部门总监，人力行政负责人处理过的绩效考核表
     * @param no 工号
     * @return
     */
    public List<OfficialPerformanceEvaluation> listHistoryByNo(String no){
        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c1 = example.createCriteria();
        OfficialPerformanceEvaluationExample.Criteria c2 = example.createCriteria();
        OfficialPerformanceEvaluationExample.Criteria c3 = example.createCriteria();

        c1.andBoss1NoEqualTo(no);
        c2.andBoss2NoEqualTo(no);
        c3.andHrNoEqualTo(no);

        example.or(c2);
        example.or(c3);

        return mapper.selectByExample(example);
    }

    /**
     * 根据考核状态，获取绩效考核表
     * @param status 考核状态（如果传入null，则返回全部绩效考核信息）
     * @return
     */
    public List<OfficialPerformanceEvaluation> listByStatus(String status){

        if (StringUtils.isBlank(status)){
            return mapper.selectByExample(null);
        }

        OfficialPerformanceEvaluationExample example = new OfficialPerformanceEvaluationExample();
        OfficialPerformanceEvaluationExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(status);
        return mapper.selectByExample(example);
    }

    /**
     * 结合分页功能的Example条件 查询
     * @param example 条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页条数
     * @return
     */
    public List<OfficialPerformanceEvaluation> listByExampleLimit(OfficialPerformanceEvaluationExample example,
                                                                  Integer limitStart, Integer limitEnd){
        return mapper.listOfficialEvaluation(example, limitStart ,limitEnd);
    }

    /**
     * 判断userNo这个用户，是否为绩效考核表的直属上司
     * @param id 绩效考核表id
     * @param userNo 要判断的用户工号
     * @return
     */
    public boolean validUserIsOfficialEvaluationBoss1(String id, String userNo){
        int num = mapperSql.countOfficialEvaluationByIdAndBossNo(id, userNo);
        if (num > 0){
            //说明是直属上司
            return true;
        }
        return false;
    }

    /**
     * 根据id获取绩效考核表信息
     * @param id
     * @return
     */
    public OfficialPerformanceEvaluation getById(String id){
        return mapper.selectByPrimaryKey(id);
    }


}
