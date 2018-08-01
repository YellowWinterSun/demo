package com.dayi.demo.dao;

import com.dayi.demo.mapper.RoleToUrlMapper;
import com.dayi.demo.mapper.SystemUrlMapper;
import com.dayi.demo.mapper.sql.UrlMapperSql;
import com.dayi.demo.model.RoleToUrlExample;
import com.dayi.demo.model.SystemUrl;
import com.dayi.demo.model.SystemUrlExample;
import com.dayi.demo.model.modelEnum.UrlStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限Dao,角色权限中间表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
@Repository
public class SystemUrlDao {
    @Autowired
    private SystemUrlMapper urlMapper;
    @Autowired
    private RoleToUrlMapper roleToUrlMapper;
    @Autowired
    private UrlMapperSql urlMapperSql;

    /**
     * 根据url地址查找Url对象
     * @param url URL
     * @return 没有返回 null
     */
    public SystemUrl getUrlByUrl(String url){
        SystemUrlExample example = new SystemUrlExample();
        SystemUrlExample.Criteria c = example.createCriteria();
        c.andUrlEqualTo(url);

        List<SystemUrl> list = urlMapper.selectByExample(example);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据id获取权限信息
     * @param id
     * @return
     */
    public SystemUrl getById(String id){
        return urlMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据传入的权限id集合，以及状态，搜索信息
     * @param listIds 权限id集合 (如果传入为空，则返回空)
     * @param status 状态（可选）
     * @return
     */
    public List<SystemUrl> listUrlByIds(List<String> listIds, String status){
        if (null == listIds || listIds.isEmpty()){
            return new ArrayList<>();
        }

        SystemUrlExample example = new SystemUrlExample();
        SystemUrlExample.Criteria c = example.createCriteria();

        c.andIdIn(listIds);

        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        example.setOrderByClause("parent_id ASC");
        return urlMapper.selectByExample(example);
    }

    /**
     * 根据传入的权限id集合，以及状态。搜索不在此id范围内的权限信息
     * @param listIds 权限id集合（如果传入为空，则返回全部权限）
     * @param status 状态（可选）
     * @return
     */
    public List<SystemUrl> listUrlNotInIds(List<String> listIds, String status){
        SystemUrlExample example = new SystemUrlExample();
        SystemUrlExample.Criteria c = example.createCriteria();
        example.setOrderByClause("parent_id ASC");

        if (null == listIds || listIds.isEmpty()){
            //空
            if (StringUtils.isNotBlank(status)){
                c.andStatusEqualTo(status);
                return urlMapper.selectByExample(example);
            }
            return urlMapper.selectByExample(example);
        }

        c.andIdNotIn(listIds);
        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }
        return urlMapper.selectByExample(example);
    }

    /**
     * 传入的角色集合，是否拥有某权限。若权限不可用，也会返回false
     * @param roleIds 要判断的角色id集合
     * @param url 权限URL
     * @return
     */
    public boolean rolesExistsUrl(List<String> roleIds, String url){
        //获取url对应的urlID
        SystemUrlExample example1 = new SystemUrlExample();
        SystemUrlExample.Criteria c1 = example1.createCriteria();
        c1.andUrlEqualTo(url);

        List<SystemUrl> list1 = urlMapper.selectByExample(example1);
        //如果没有当前url，则返回false
        if (list1.size() <= 0){
            return false;
        }

        //因为url是唯一的，所以取出第一个即可
        SystemUrl systemUrl = list1.get(0);
        //判断url是否可用，不可用返回false
        if (systemUrl.getStatus().equals(UrlStatusEnum.DISABLED.getValue())){
            //不可用
            return false;
        }

        //判断角色是否有当前权限
        RoleToUrlExample example2 = new RoleToUrlExample();
        RoleToUrlExample.Criteria c2 = example2.createCriteria();
        c2.andRoleIdIn(roleIds);
        c2.andUrlIdEqualTo(systemUrl.getId());

        int num = roleToUrlMapper.countByExample(example2);
        if (num > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据条件，分页的搜索权限信息
     * @param example 筛选条件类
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @return
     */
    public List<SystemUrl> listUrlLimit(SystemUrlExample example, Integer limitStart, Integer limitEnd){
        return urlMapper.listUrlLimit(example, limitStart, limitEnd);
    }

    /**
     * 获取某用户拥有的可用权限列表
     * @param userId 用户id
     * @return
     */
    public List<String> listUserEnableUrl(String userId){
        return urlMapperSql.listUserEnableUrl(userId);
    }

    /**
     * 根据条件，获得匹配的记录总数
     * @param example
     * @return
     */
    public int countByExample(SystemUrlExample example){
        return urlMapper.countByExample(example);
    }

    /**
     * 根据id，更新权限信息
     * @param urlSelective
     * @return
     */
    public int updateUrl(SystemUrl urlSelective){
        return urlMapper.updateByPrimaryKeySelective(urlSelective);
    }
}
