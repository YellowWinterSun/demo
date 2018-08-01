package com.dayi.demo.service;

import com.dayi.demo.model.User;
import com.dayi.demo.msgEnum.ResStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * 用户服务的接口
 * 专注于用户表的服务
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public interface UserService {

    /**
     * 根据手机号码获取用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 根据UUID获取用户
     * @param id UUID
     * @return 没有则NULL
     */
    User getUserById(String id);

    /**
     * 根据工号获取用户
     * @param no
     * @return
     */
    User getUserByNo(String no);

    /**
     * 用户管理中user表格数据的展示服务,不需要进行搜索的条件置NULL
     * @param name 姓名（模糊搜索）
     * @param phone 电话号码（模糊搜索）
     * @param no 工号（模糊搜索）
     * @param limitStart 分页起始下标
     * @param limitEnd 记录个数
     * @param orderByClause 排序规则
     * @return （1）“list”：符合条件的user集合；（2）“total”：符合条件的总数（未分页的记录数）
     */
    Map<String, Object> listUserTable(String name, String phone,
                                      String no, Integer limitStart, Integer limitEnd, String orderByClause);

    /**
     * 根据条件，搜索用户信息
     * @param listIds 用户id范围（在这个范围内取值)
     * @param nameLike 用户名字(模糊搜索）（可选）
     * @param limitStart 分页起始下标
     * @param limitEnd 分页记录数
     * @param order 排序字段
     * @param sort 排序规则
     * @return
     */
    List<User> listUser(List<String> listIds, String nameLike, Integer limitStart, Integer limitEnd, String order, String sort);

    /**
     * 增加新用户(并为其设置为默认初始角色)
     * @param newUser 封装好的新用户
     * @return false的情况：（1）工号，手机号码重复。（2）非空属性为NULL
     */
    boolean addUser(User newUser);

    /**
     * 根据id，工号，手机号码删除一个用户。不需要的条件置NULL，至少传入一个参数。
     * @param id UUID
     * @param no 工号
     * @param phone 手机号码
     * @return 成功与否
     */
    boolean deleteUser(String id, String phone, String no);

    /**
     * 根据UUID，选择性更新用户信息。如果传入password，无需加密
     * @param userSelective User对象
     * @return 失败返回状态码，成功返回null
     */
    ResStatusEnum updateUser(User userSelective);

    /**
     * 统计某个岗位的用户数量
     * @param jobId 岗位id
     * @param jobName 岗位名称
     * @return
     */
    int countUserByJob(String jobId, String jobName);

    /**
     * 根据直属上司工号，获取其所有下属
     * @param directSupervisorNo 直属上司工号
     * @return
     */
    List<User> listUserByDirectSupervisorNo(String directSupervisorNo);

    /**
     * 根据部门名集合，获取在其部门的所有用户
     * @param departmentNames 要查询的部门名称集合
     * @return
     */
    List<User> listUserByDepartments(List<String> departmentNames);

    /**
     * 根据部门总监工号，获取其所有部门下属（不返回下级分部门的员工）
     * @param majordomoNo 部门总监工号
     * @return
     */
    List<User> listUserByDepartmentMajordomo(String majordomoNo);

    /**
     * 根据部门总监工号，获取其所有部门下属（返回部门下级分部门的所有员工，递归进行）
     * @param majordomoNo 部门总监工号
     * @return
     */
    List<User> listUserByDepartmentMajordomoRecurve(String majordomoNo);

    /**
     * 获取系统中所有用户信息
     * @return
     */
    List<User> listUser();

    /**
     * 获取不在所给范围内的其他所有用户信息（传入空，则返回全部）
     * @param userIds 需要排除的用户id范围
     * @return
     */
    List<User> listUserNotInIds(List<String> userIds);

    /**
     * 判断bossNo是否为userNo的上司（上级部门负责人或者直属部门负责人或者直属上司，都会返回true)
     * @param bossNo 要验证的上司工号
     * @param userNo 下属工号
     * @return
     */
    boolean validBossIsUserBoss(String bossNo, String userNo);
}
