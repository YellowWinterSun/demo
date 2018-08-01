package com.dayi.demo.service.impl;

import com.dayi.demo.controller.model.SystemUrlRes;
import com.dayi.demo.dao.RoleToUrlDao;
import com.dayi.demo.dao.SystemUrlDao;
import com.dayi.demo.model.Role;
import com.dayi.demo.model.RoleToUrl;
import com.dayi.demo.model.SystemUrl;
import com.dayi.demo.model.SystemUrlExample;
import com.dayi.demo.model.modelEnum.UrlStatusEnum;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UrlService;
import com.dayi.demo.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 权限服务类的实现
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/6
 */
@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private SystemUrlDao systemUrlDao;
    @Autowired
    private RoleToUrlDao roleToUrlDao;

    @Autowired
    private RoleService roleService;

    @Override
    public SystemUrl getUrlByUrl(String url) {
        if (StringUtils.isBlank(url)){
            return null;
        }

        return systemUrlDao.getUrlByUrl(url);
    }

    @Override
    public List<SystemUrl> listRoleExistsUrl(String roleId, String status) {
        if (StringUtils.isBlank(roleId)){
            return new ArrayList<>();
        }

        //获得当前角色拥有的权限id集合
        List<String> listUrlIds = roleToUrlDao.listRoleUrls(roleId);
        if (listUrlIds.isEmpty()){
            return new ArrayList<>();
        }
        return systemUrlDao.listUrlByIds(listUrlIds, status);
    }

    @Override
    public List<SystemUrl> listRoleNotExistsUrl(String roleId, String status) {
        if (StringUtils.isBlank(roleId)){
            return new ArrayList<>();
        }

        //获得当前角色拥有的权限id集合
        List<String> listUrlIds = roleToUrlDao.listRoleUrls(roleId);

        return systemUrlDao.listUrlNotInIds(listUrlIds, status);
    }

    @Override
    public void updateRoleUrl(String roleId, List<String> undoUrlIds, List<String> doUrlIds) {
        if (null != undoUrlIds && !undoUrlIds.isEmpty()){
            //剥夺角色的权限
            roleToUrlDao.deleteByRoleAndUrls(roleId, undoUrlIds);
        }

        if (null != doUrlIds && !doUrlIds.isEmpty()){
            //为角色设置新权限
            for (String urlId : doUrlIds){
                RoleToUrl record = new RoleToUrl();
                record.setId(UUIDUtil.getUUID());
                record.setRoleId(roleId);
                record.setUrlId(urlId);

                roleToUrlDao.addRoleUrl(record);
            }
        }
        return;
    }

    @Override
    public Map<String, Object> listUrlRes(String id, String urlLike, Integer limitStart, Integer limitEnd, String order, String sort) {
        //封装搜索条件
        SystemUrlExample example = new SystemUrlExample();
        SystemUrlExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(id)){
            c.andIdEqualTo(id);
        }
        else {
            if (StringUtils.isNotBlank(urlLike)) {
                c.andUrlLike("%" + urlLike + "%");

                SystemUrlExample.Criteria c2 = example.createCriteria();
                c2.andNameLike("%" + urlLike + "%");
                example.or(c2);
            }
        }

        if (StringUtils.isNotBlank(order)){
            example.setOrderByClause(order + " " + sort);
        }

        List<SystemUrl> list = systemUrlDao.listUrlLimit(example, limitStart, limitEnd);

        int total = systemUrlDao.countByExample(example);
        //二次封装为SystemUrlRes类
        List<SystemUrlRes> listResult = new ArrayList<>();
        for (SystemUrl url : list){
            //获得父级URL
            if (StringUtils.isNotBlank(url.getParentId())) {
                SystemUrl parentUrl = systemUrlDao.getById(url.getParentId());
                SystemUrlRes res = new SystemUrlRes(url, parentUrl.getName());
                listResult.add(res);
            }
            else {
                SystemUrlRes res = new SystemUrlRes(url, "");
                listResult.add(res);
            }
        }

        //封装结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", listResult);
        resultMap.put("total", total);
        resultMap.put("rows", listResult.size());

        return resultMap;
    }

    @Override
    public List<String> listUserEnableUrl(String userId) {
        return systemUrlDao.listUserEnableUrl(userId);
    }

    @Override
    public void updateUrlStatus(List<String> urlIds) {
        for (String urlId : urlIds){
            //获得当前的权限信息
            SystemUrl url = systemUrlDao.getById(urlId);
            String status = url.getStatus();

            //更新的权限信息对象
            SystemUrl updateUrl = new SystemUrl();
            updateUrl.setId(url.getId());
            updateUrl.setUpdatetime(new Date());
            if (status.equals(UrlStatusEnum.NORMAL.getValue())){
                //当前权限是正常状态，则变为禁用
                updateUrl.setStatus(UrlStatusEnum.DISABLED.getValue());
            } else {
                updateUrl.setStatus(UrlStatusEnum.NORMAL.getValue());
            }

            systemUrlDao.updateUrl(updateUrl);
        }
        return;
    }

    @Override
    public void deleteRoleToUrlByRoleId(String roleId) {
        roleToUrlDao.deleteByRoleId(roleId);
        return;
    }

    @Override
    public void cleanVoidRecord() {
        //获取系统所有角色
        List<Role> list = roleService.listRole(null);
        //封装为角色ID集合
        List<String> listIds = new ArrayList<>();
        for (Role role : list){
            listIds.add(role.getId());
        }

        roleToUrlDao.deleteVoidRecord(listIds);
        return;
    }
}
