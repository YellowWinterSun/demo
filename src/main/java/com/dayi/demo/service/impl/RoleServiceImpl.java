package com.dayi.demo.service.impl;

import com.dayi.demo.dao.RoleDao;
import com.dayi.demo.dao.SystemUrlDao;
import com.dayi.demo.dao.UserDao;
import com.dayi.demo.dao.UserToRoleDao;
import com.dayi.demo.model.Role;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.RoleStatusEnum;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UrlService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色权限服务类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserToRoleDao userToRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SystemUrlDao systemUrlDao;

    @Autowired
    private UrlService urlService;

    /**
     * 判断某个用户是否拥有某权限
     *
     * @param userNo 用户工号
     * @param url    URL
     * @return
     */
    @Override
    public boolean validUserUrl(String userNo, String url) {
        //获取用户id
        User user = userDao.getUserByNo(userNo);
        //获取用户的角色信息
        List<String> listRoleIds = userToRoleDao.listRoleIdsByUserId(user.getId());
        if (listRoleIds.size() <= 0){
            return false;
        }
        List<Role> listRole = roleDao.listRoleByIds(listRoleIds, RoleStatusEnum.NORMAL.getValue());
        if (listRole.isEmpty()){
            return false;
        }
        //再次封装用户的角色id（过滤掉不可用的角色）
        List<String> listNormalRoleIds = new ArrayList<>();
        for (Role role : listRole){
            listNormalRoleIds.add(role.getId());
        }

        return systemUrlDao.rolesExistsUrl(listNormalRoleIds, url);
    }

    @Override
    public List<Role> listRole(String status) {
        return roleDao.listRole(status);
    }

    @Override
    public Role getByName(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }
        return roleDao.getByName(name);
    }

    @Override
    public Role getById(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return roleDao.getById(id);
    }

    @Override
    public List<String> listRoleUsers(String roleId) {
        return userToRoleDao.listUserIdsByRoleId(roleId);
    }

    @Override
    public void addRoleUser(String roleId, List<String> userIds) {
        if (StringUtils.isBlank(roleId) || null == userIds){
            return;
        }

        for (String userId : userIds){
            try {
                userToRoleDao.add(userId, roleId);
            } catch (RuntimeException e){
                /*截获不做处理是因为，在这一步出现异常，原因是因为用户id，角色id两个字段做了联合唯一索引。因此如果插入了
                重复的用户角色对，MyBatis插入时就会报唯一索引错误。出现这种情况，不需要做处理，直接让程序继续完成剩下的
                数据库插入工作。因为这种情况一般是用户恶意操作才会出现，概率很低，所以不在添加之前，加入有无重复的判断。减少
                后台没必要的开销
                 */
            }
        }
        return;
    }

    @Override
    public void addRoleUser(String roleId, String userId) {
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(userId)){
            return;
        }

        userToRoleDao.add(userId, roleId);
        return;
    }

    @Override
    public void deleteRoleUser(String roleId, List<String> userIds) {
        if (null == userIds || userIds.isEmpty()){
            return;
        }

        userToRoleDao.delete(roleId, userIds);
        return;
    }

    @Override
    public void deleteUserToRoleByUserId(String userId) {
        if (StringUtils.isBlank(userId)){
            return;
        }

        userToRoleDao.deleteByUserId(userId);
        return;
    }

    @Override
    public void addRole(String name, String remark) {
        Role role = new Role();
        role.setId(UUIDUtil.getUUID());
        role.setName(name);
        if (null != remark){
            role.setRemark(remark);
        }
        role.setStatus(RoleStatusEnum.NORMAL.getValue());
        roleDao.addRole(role);
        return;
    }

    @Override
    public void updateRole(Role roleSelective) {
        if (null == roleSelective){
            return;
        }
        if (null == roleSelective.getUpdatetime()){
            roleSelective.setUpdatetime(new Date());
        }

        roleDao.updateRole(roleSelective);
        return;
    }

    @Override
    public void deleteRole(String id) {
        //删除角色表
        roleDao.deleteRole(id);
        //删除用户角色表
        userToRoleDao.deleteByRoleId(id);
        //删除角色权限表
        urlService.deleteRoleToUrlByRoleId(id);
        return;
    }

    @Override
    public void cleanNotExistsUserToRole() {
        //获取系统中现有的所有用户
        List<User> listUser = userDao.listUser(null, null, null, null, null, null);
        //获得系统中现有的所有角色
        List<Role> listRole = roleDao.listRole(null);

        //记录用户id集
        List<String> listUserIds = new ArrayList<>();
        for (User user : listUser){
            listUserIds.add(user.getId());
        }

        //记录角色id集
        List<String> listRoleIds = new ArrayList<>();
        for (Role role : listRole){
            listRoleIds.add(role.getId());
        }

        userToRoleDao.deleteVoidRecord(listUserIds, listRoleIds);
    }
}
