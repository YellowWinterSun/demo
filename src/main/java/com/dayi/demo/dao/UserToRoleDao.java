package com.dayi.demo.dao;

import com.dayi.demo.mapper.UserToRoleMapper;
import com.dayi.demo.model.UserToRole;
import com.dayi.demo.model.UserToRoleExample;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户-角色 中间表Dao
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
@Repository
public class UserToRoleDao {

    @Autowired
    private UserToRoleMapper mapper;

    /**
     * 根据用户id，获取该用户的角色id列表
     * @param userId
     * @return
     */
    public List<String> listRoleIdsByUserId(String userId){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c = example.createCriteria();
        c.andUserIdEqualTo(userId);

        List<UserToRole> list = mapper.selectByExample(example);
        if (list.size() <= 0){
            return new ArrayList<String>();
        }

        List<String> listResult = new ArrayList<>();
        for (UserToRole data : list){
            listResult.add(data.getRoleId());
        }
        return listResult;
    }

    /**
     * 根据角色id，获取该角色的所有用户id
     * @param roleId
     * @return
     */
    public List<String> listUserIdsByRoleId(String roleId){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c = example.createCriteria();
        c.andRoleIdEqualTo(roleId);

        List<UserToRole> list = mapper.selectByExample(example);
        if (list.isEmpty()){
            return new ArrayList<>();
        }

        List<String> listResult = new ArrayList<>();
        for (UserToRole data : list){
            listResult.add(data.getUserId());
        }
        return listResult;
    }

    /**
     * 新增用户-角色关联
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    public int add(String userId, String roleId){
        UserToRole record = new UserToRole();
        record.setId(UUIDUtil.getUUID());
        record.setUserId(userId.trim());
        record.setRoleId(roleId.trim());

        return mapper.insertSelective(record);
    }

    /**
     * 删除 用户-角色 关联
     * @param roleId 要删除的角色id
     * @param userIds 要删除的用户id范围
     * @return
     */
    public int delete(String roleId, List<String> userIds){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c = example.createCriteria();
        c.andRoleIdEqualTo(roleId);
        c.andUserIdIn(userIds);

        return mapper.deleteByExample(example);
    }

    /**
     * 根据 角色id，删除所有关联
     * @param roleId 角色id
     * @return
     */
    public int deleteByRoleId(String roleId){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c = example.createCriteria();
        c.andRoleIdEqualTo(roleId);

        return mapper.deleteByExample(example);
    }

    /**
     * 根据 用户id，删除所有关联
     * @param userId 用户id
     * @return
     */
    public int deleteByUserId(String userId){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c = example.createCriteria();
        c.andUserIdEqualTo(userId);

        return mapper.deleteByExample(example);
    }

    /**
     * 删除中间表中的所有无效数据。（PS：无效数据指，用户或者角色已经不存在，但中间表仍存在其的记录）
     * @param userIds 有效的用户id集合
     * @param roleIds 有效的角色id集合
     * @return
     */
    public int deleteVoidRecord(List<String> userIds, List<String> roleIds){
        UserToRoleExample example = new UserToRoleExample();
        UserToRoleExample.Criteria c1 = example.createCriteria();
        c1.andUserIdNotIn(userIds);

        UserToRoleExample.Criteria c2 = example.createCriteria();
        c2.andRoleIdNotIn(roleIds);

        example.or(c2);

        return mapper.deleteByExample(example);
    }
}
