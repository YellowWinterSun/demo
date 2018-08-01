package com.dayi.demo.dao;

import com.dayi.demo.mapper.DimensionalityMapper;
import com.dayi.demo.model.Dimensionality;
import com.dayi.demo.model.DimensionalityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考核维度Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/21
 */
@Repository
public class DimensionalityDao {

    @Autowired
    private DimensionalityMapper dimensionalityMapper;

    /**
     * 根据考核维度ID获取考核维度对象
     * @param id UUID
     * @return
     */
    public Dimensionality getDimensionalityById(String id){
        return dimensionalityMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取系统中的考核维度信息，默认以优先级降序排序
     * @return
     */
    public List<Dimensionality> listDimensionalityOrderbyLevel(){
        DimensionalityExample example = new DimensionalityExample();
        example.setOrderByClause("level DESC");
        return dimensionalityMapper.selectByExample(example);
    }
}
