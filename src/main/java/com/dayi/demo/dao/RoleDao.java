package com.dayi.demo.dao;

import com.dayi.demo.mapper.RoleMapper;
import com.dayi.demo.model.Role;
import com.dayi.demo.model.RoleExample;
import com.dayi.demo.model.modelEnum.RoleStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
@Repository
public class RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取角色
     * @param listIds 角色ID集合
     * @param status 状态（可选）
     * @return
     */
    public List<Role> listRoleByIds(List<String> listIds, String status){
        RoleExample example = new RoleExample();
        RoleExample.Criteria c = example.createCriteria();
        c.andIdIn(listIds);

        if (StringUtils.isNotBlank(status)){
            c.andStatusEqualTo(status);
        }

        return roleMapper.selectByExample(example);
    }

    /**
     * 获取角色列表
     * @param status 状态（可选）
     * @return
     */
    public List<Role> listRole(String status){
        if (StringUtils.isNotBlank(status)){
            RoleExample example = new RoleExample();
            RoleExample.Criteria c = example.createCriteria();
            c.andStatusEqualTo(status);

            return roleMapper.selectByExample(example);
        }
        return roleMapper.selectByExample(null);
    }

    /**
     * 根据id获取角色信息
     * @param id 角色id
     * @return
     */
    public Role getById(String id){
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据角色名获取角色信息
     * @param roleName
     * @return
     */
    public Role getByName(String roleName){
        RoleExample example = new RoleExample();
        RoleExample.Criteria c = example.createCriteria();
        c.andNameEqualTo(roleName);

        List<Role> list = roleMapper.selectByExample(example);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    /**
     * 增加一个新角色
     * @param roleSelective
     * @return
     */
    public int addRole(Role roleSelective){
        return roleMapper.insertSelective(roleSelective);
    }

    /**
     * 根据角色id，修改角色信息
     * @param roleSelective
     * @return
     */
    public int updateRole(Role roleSelective){
        return roleMapper.updateByPrimaryKeySelective(roleSelective);
    }

    /**
     * 根据id，删除角色信息
     * @param id
     * @return
     */
    public int deleteRole(String id){
        return roleMapper.deleteByPrimaryKey(id);
    }
}
