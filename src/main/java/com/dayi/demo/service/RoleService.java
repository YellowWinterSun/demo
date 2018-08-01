package com.dayi.demo.service;

import com.dayi.demo.model.Role;

import java.util.List;

/**
 * 角色权限服务
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
public interface RoleService {

    /**
     * 判断某个用户是否拥有某权限（权限,角色需可用)
     * @param userNo 用户工号
     * @param url URL
     * @return
     */
    boolean validUserUrl(String userNo, String url);

    /**
     * 获取角色信息
     * @param status 筛选状态（可选）
     * @return
     */
    List<Role> listRole(String status);

    /**
     * 根据角色名称，获取角色信息
     * @param name
     * @return
     */
    Role getByName(String name);

    /**
     * 根据角色id，获取角色信息
     * @param id
     * @return
     */
    Role getById(String id);

    /**
     * 获取当前角色下的所有用户id集
     * @param roleId
     * @return
     */
    List<String> listRoleUsers(String roleId);

    /**
     * 为角色新增多个用户
     * @param roleId 角色id
     * @param userIds 用户id集合
     */
    void addRoleUser(String roleId, List<String> userIds);

    /**
     * 为角色新增一个用户
     * @param roleId 角色id
     * @param userId 用户id
     */
    void addRoleUser(String roleId, String userId);

    /**
     * 为角色去除多个用户
     * @param roleId 角色id
     * @param userIds 要去除的用户id
     */
    void deleteRoleUser(String roleId, List<String> userIds);

    /**
     * 删除用户 - 角色中间表，根据用户id
     * @param userId
     */
    void deleteUserToRoleByUserId(String userId);

    /**
     * 新增角色
     * @param name 新角色名称
     * @param remark 角色描述
     */
    void addRole(String name, String remark);

    /**
     * 根据id，更新角色信息
     * @param roleSelective
     */
    void updateRole(Role roleSelective);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(String id);

    /**
     * 用户-角色中间表清理工作
     * 清理残留无用的中间表记录
     */
    void cleanNotExistsUserToRole();
}
