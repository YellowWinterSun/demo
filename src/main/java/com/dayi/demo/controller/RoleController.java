package com.dayi.demo.controller;

import com.dayi.demo.model.Role;
import com.dayi.demo.model.SystemUrl;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.RoleStatusEnum;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UrlService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 角色权限管理 - 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/5
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UrlService urlService;

    //记录系统默认角色的ID
    final HashSet<String> defaultRoleIdSet = new HashSet<String>(){{
       add("23828f9c18174efb95aeb15d0f119bbe");     //绩效考核表人事行政负责人（默认角色）
       add("60f1a73be94343268d01eec12ab31845");     //初始角色 （默认角色）
       add("a378c9e957f84d69a88433ca58d0254c");     //系统超级管理员 （默认角色）
    }};

    /**
     * 跳转至角色权限管理主页
     * @return
     */
    @RequestMapping
    public String toIndex(){
        return "sys/roleManage";
    }

    /**
     * 获取系统的角色信息（不区分可用 ，不可用)
     * @return
     */
    @RequestMapping("listRole")
    @ResponseBody
    public Msg listRole(){
        List<Role> list = roleService.listRole(null);
        return Msg.success(null).add("list", list);
    }

    /**
     * 获取当前角色下的用户
     * @param roleId
     * @return
     */
    @RequestMapping("/listRoleUser")
    @ResponseBody
    public Msg listRoleUser(String roleId, String name, Integer limitStart,
                            Integer limitEnd, String order, String sort){
        if (StringUtils.isBlank(roleId)){
            return Msg.success(null).add("list", new ArrayList<>());
        }

        List<String> listUserIds = roleService.listRoleUsers(roleId);
        int total = listUserIds.size();
        int rows = 0;
        List<User> list = new ArrayList<>();

        if (total > 0){
            list = userService.listUser(listUserIds, name, limitStart, limitEnd, order, sort);
            rows = list.size();
        }

        return Msg.success(null).add("list", list)
                .add("total", total)
                .add("rows", rows);
    }

    /**
     * 根据角色id，获取角色信息
     * @param roleId
     * @return
     */
    @RequestMapping("/getRole")
    @ResponseBody
    public Role getRole(String roleId){
        Role role = roleService.getById(roleId);
        return role;
    }

    /**
     * 新增角色
     * @param name
     * @param remark
     * @return
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public Msg addRole(String name, String remark){
        //校验参数
        if (StringUtils.isBlank(name)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //校验角色名是否存在
        name = name.trim();
        if (null != roleService.getByName(name)){
            //角色名重复了
            return Msg.res(ResStatusEnum.ROLE_NAME_REPEAT);
        }

        roleService.addRole(name, remark);
        return Msg.success(null);
    }

    /**
     * 更新角色
     * @param name
     * @param remark
     * @param status
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public Msg updateRole(String id, String name, String remark, RoleStatusEnum status){
        //获取角色信息
        Role oldRole = roleService.getById(id);
        if (null == oldRole){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        if (StringUtils.isBlank(name) || null == status){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        name = name.trim();
        //进行系统默认角色判断
        if (defaultRoleIdSet.contains(id)){
            //当前操作的是系统默认角色，系统默认的3个初始角色不允许修改名字和描述
            if (!name.equals(oldRole.getName()) || !remark.equals(oldRole.getRemark())){
                return Msg.res(ResStatusEnum.ROLE_DEFAULT_CANNOT_UPDATE);
            }

            Role newRole = new Role();
            newRole.setId(id);
            newRole.setUpdatetime(new Date());
            newRole.setStatus(status.getValue());
            roleService.updateRole(newRole);
            return Msg.success(null);
        }

        //如果角色名修改了，则进行重复判断
        if (!name.equals(oldRole.getName())){
            if (null != roleService.getByName(name)){
                //角色名重复了
                return Msg.res(ResStatusEnum.ROLE_NAME_REPEAT);
            }
        }

        Role newRole = new Role();
        newRole.setId(id);
        newRole.setUpdatetime(new Date());
        if (!name.equals(oldRole.getName())){
            newRole.setName(name);
        }
        newRole.setRemark(remark);
        newRole.setStatus(status.getValue());

        roleService.updateRole(newRole);
        return Msg.success(null);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Msg deleteRole(String id){
        Role role = roleService.getById(id);
        if (null == role){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //进行系统默认角色判断,系统默认角色不允许删除
        if (defaultRoleIdSet.contains(id)){
            return Msg.res(ResStatusEnum.ROLE_DEFAULT_CANNOT_UPDATE);
        }

        roleService.deleteRole(id);
        return Msg.success(null);
    }

    /**
     * 为角色新增用户
     * @param roleId 角色id
     * @param userIds 多个用户id字符串（用逗号隔开）
     * @return
     */
    @RequestMapping("/addRoleUser")
    @ResponseBody
    public Msg addRoleUser(String roleId, String userIds){
        //校验参数
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(userIds)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //解析userIds
        List<String> listUserIds = new ArrayList<>();
        for (String userId : userIds.split(",")){
            listUserIds.add(userId);
        }

        roleService.addRoleUser(roleId, listUserIds);

        return Msg.success(null);
    }

    /**
     * 角色权限管理 - 为角色去除多个用户
     * @param roleId 角色id
     * @param userIds 用户id集字符串（多个用户id用逗号隔开)
     * @return
     */
    @RequestMapping("/deleteRoleUser")
    @ResponseBody
    public Msg deleteRoleUser(String roleId, String userIds){
        //校验参数
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(userIds)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //解析userIds
        List<String> listUserIds = new ArrayList<>();
        for (String userId : userIds.split(",")){
            listUserIds.add(userId);
        }

        roleService.deleteRoleUser(roleId, listUserIds);

        return Msg.success(null);
    }

    /**
     * 获取某角色没有拥有的用户集
     * @param roleId
     * @return
     */
    @RequestMapping("/listRoleWithoutUsers")
    @ResponseBody
    public Msg listRoleWithoutUsers(String roleId){
        if (StringUtils.isBlank(roleId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //获取当前角色拥有的用户ID集
        List<String> listUserIds = roleService.listRoleUsers(roleId);
        //获取不在上述范围的所有其他用户信息
        List<User> listResult = userService.listUserNotInIds(listUserIds);

        return Msg.success(null).add("list", listResult);
    }

    /**
     * 根据角色id，获取它拥有的权限，以及没有的权限集合
     * @param roleId
     * @return
     */
    @RequestMapping("/listRoleUrls")
    @ResponseBody
    public Msg listRoleUrls(String roleId){
        if (StringUtils.isBlank(roleId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        List<SystemUrl> listExists = urlService.listRoleExistsUrl(roleId, null);
        List<SystemUrl> listNotExists = urlService.listRoleNotExistsUrl(roleId, null);

        return Msg.success(null).add("listExists", listExists).add("listNotExists", listNotExists);
    }

    /**
     * 更新角色的权限配置
     * @param roleId 角色id
     * @param leftArrayStr 即将剥夺的权限id集合
     * @param rightArrayStr 即将赋予的权限id集合
     * @return
     */
    @RequestMapping("/updateRoleUrl")
    @ResponseBody
    public Msg updateRoleUrl(String roleId, String leftArrayStr, String rightArrayStr){
        if (StringUtils.isBlank(roleId)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //进行系统默认角色判断,系统默认角色不允许删除
        if (defaultRoleIdSet.contains(roleId)){
            return Msg.res(ResStatusEnum.ROLE_DEFAULT_CANNOT_UPDATE);
        }

        //两个ArrayStr都为空，则直接返回，不进行处理
        if (StringUtils.isBlank(leftArrayStr) && StringUtils.isBlank(rightArrayStr)){
            return Msg.success(null);
        }

        //记录即将要剥夺的Url权限
        List<String> listUndoUrlIds = new ArrayList<>();
        if (StringUtils.isNotBlank(leftArrayStr)){
            for (String urlId : leftArrayStr.split(",")){
                listUndoUrlIds.add(urlId.trim());
            }
        }

        //记录即将要赋予的Url权限
        List<String> listDoUrlIds = new ArrayList<>();
        if (StringUtils.isNotBlank(rightArrayStr)){
            for (String urlId : rightArrayStr.split(",")){
                listDoUrlIds.add(urlId.trim());
            }
        }

        urlService.updateRoleUrl(roleId, listUndoUrlIds, listDoUrlIds);

        return Msg.success(null);
    }
}
