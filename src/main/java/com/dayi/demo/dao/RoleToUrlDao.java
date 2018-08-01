package com.dayi.demo.dao;

import com.dayi.demo.mapper.RoleToUrlMapper;
import com.dayi.demo.model.RoleToUrl;
import com.dayi.demo.model.RoleToUrlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 - 权限 中间表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/6
 */
@Repository
public class RoleToUrlDao {

    @Autowired
    private RoleToUrlMapper mapper;

    /**
     * 根据角色id，搜索信息（获取角色拥有的url）
     * @param roleId 角色id
     * @return Url的ID集合
     */
    public List<String> listRoleUrls(String roleId){
        RoleToUrlExample example = new RoleToUrlExample();
        RoleToUrlExample.Criteria c = example.createCriteria();
        c.andRoleIdEqualTo(roleId);

        List<RoleToUrl> list = mapper.selectByExample(example);
        if (list.isEmpty()){
            return new ArrayList<>();
        }

        List<String> listIds = new ArrayList<>();
        for (RoleToUrl data : list){
            listIds.add(data.getUrlId());
        }

        return listIds;
    }

    /**
     * 增加新的配置
     * @param roleToUrl
     * @return
     */
    public int addRoleUrl(RoleToUrl roleToUrl){
        return mapper.insertSelective(roleToUrl);
    }

    /**
     * 删除配置，根据角色id和要删除的权限id集合
     * @param roleId 角色id
     * @param urlIds 权限id集（不能为空）
     * @return
     */
    public int deleteByRoleAndUrls(String roleId, List<String> urlIds){
        RoleToUrlExample example = new RoleToUrlExample();
        RoleToUrlExample.Criteria c = example.createCriteria();
        c.andUrlIdIn(urlIds);
        c.andRoleIdEqualTo(roleId);

        return mapper.deleteByExample(example);
    }

    /**
     * 删除角色-权限中间表的无用数据
     * @param roleIds 系统现存的角色id集合
     * @return
     */
    public int deleteVoidRecord(List<String> roleIds){
        RoleToUrlExample example = new RoleToUrlExample();
        RoleToUrlExample.Criteria c = example.createCriteria();
        c.andRoleIdNotIn(roleIds);

        return mapper.deleteByExample(example);
    }

    /**
     * 根据角色ID，删除中间表信息
     * @param roleId 角色id
     * @return
     */
    public int deleteByRoleId(String roleId){
        RoleToUrlExample example = new RoleToUrlExample();
        RoleToUrlExample.Criteria c = example.createCriteria();
        c.andRoleIdEqualTo(roleId);
        return mapper.deleteByExample(example);
    }
}
