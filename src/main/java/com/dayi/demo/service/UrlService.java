package com.dayi.demo.service;

import com.dayi.demo.model.SystemUrl;

import java.util.List;
import java.util.Map;

/**
 * 权限服务类接口
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/6
 */
public interface UrlService {

    /**
     * 根据权限id获取权限
     * @param id
     * @return
     */
    SystemUrl getUrlByUrl(String url);

    /**
     * 获取角色拥有的权限
     * @param roldId 角色id
     * @param status 权限状态（可选）
     * @return
     */
    List<SystemUrl> listRoleExistsUrl(String roldId, String status);

    /**
     * 获取角色没有的权限
     * @param roleId 角色id
     * @param status 权限状态（可选）
     * @return
     */
    List<SystemUrl> listRoleNotExistsUrl(String roleId, String status);

    /**
     * 更新角色的权限配置
     * @param roleId 角色id
     * @param undoUrlIds 要剥夺的权限id集合
     * @param doUrlIds 要赋予的权限id集合
     */
    void updateRoleUrl(String roleId, List<String> undoUrlIds, List<String> doUrlIds);

    /**
     * 获取权限信息列表
     * @param id 权限id（如果传入该字段，urlLike将失效）
     * @param urlLike 权限地址,权限名字复合（模糊搜索）
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序方式
     * @return
     */
    Map<String, Object> listUrlRes(String id, String urlLike, Integer limitStart, Integer limitEnd, String order, String sort);

    /**
     * 获取某用户拥有的可用的权限
     * @param userId 用户id
     * @return
     */
    List<String> listUserEnableUrl(String userId);

    /**
     * 更新权限状态
     * @param urlIds 要更新的权限id集合
     */
    void updateUrlStatus(List<String> urlIds);

    /**
     * 根据角色id，删除中间表关联
     * @param roleId
     */
    void deleteRoleToUrlByRoleId(String roleId);

    /**
     * 角色-权限中间表清理无效数据工作
     */
    void cleanVoidRecord();
}
