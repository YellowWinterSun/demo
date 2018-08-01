package com.dayi.demo.msgEnum;

/**
 * 返回状态码枚举类。由于系统设计默认100为成功返回，200为失败返回。因此状态码从300开始<br/>
 * 201-299 公用<br/>
 * 300-399 系统模块<br/>
 * 400-499 绩效考核系统模块<br/>
 * 500-599 会议室预约模块<br/>
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/14
 */
public enum ResStatusEnum {

    /**
     * 参数为空
     */
    DATA_NULL(201, "未正确传入参数"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(300, "登录成功"),
    /**
     * 登录失败
     */
    LOGIN_FAIL(301, "登录失败，信息不匹配"),
    /**
     * 系统模块 - 信息填写不完整，请核对信息的完整性
     */
    USER_FORM_INCOMPLETE(303, "信息填写不完整，请核对信息的完整性"),
    /**
     * 系统模块 - 用户 - 手机号重复
     */
    USER_PHONE_REPEAT(304, "用户手机号码重复"),
    /**
     * 系统模块 - 用户 - 工号重复
     */
    USER_NO_REPEAT(305, "用户工号重复"),
    /**
     * 系统模块 - 用户 - 工号不允许更新
     */
    USER_NO_CANNOT_UPDATE(306, "工号不允许更新"),
    /**
     * 系统模块 - 部门岗位 - 岗位名称重复
     */
    JOB_NAME_REPEAT(307, "岗位名称重复"),
    /**
     * 系统模块 - 部门岗位 - 部门名称重复
     */
    DEPARTMENT_NAME_REPEAT(308, "部门名称重复"),
    /**
     * 系统模块 - 角色权限管理 - 角色名重复
     */
    ROLE_NAME_REPEAT(309, "角色名重复"),
    /**
     * 系统模块 - 角色权限管理 - 系统默认角色不允许修改（只允许修改状态）
     */
    ROLE_DEFAULT_CANNOT_UPDATE(310, "系统默认角色不允许修改（只允许修改状态）"),
    /**
     * 系统模块 - 权限控制 - 无法禁用权限控制功能的权限
     */
    URL_DEFAULT_CANNOT_DISABLED(311, "无法禁用[权限控制]相关的权限"),
    /**
     * 会议室预约模块 - 预约时间有误(起始时间不能大于或等于结束时间)
     */
    MEETING_TIME_ERROR(500, "起始时间不能大于或等于结束时间"),
    /**
     * 会议室预约模块 - 跨日预约
     */
    MEETING_ORDER_SUPERPASS_DAY(501, "不允许跨日预约会议室"),
    /**
     * 会议室预约模块 - 时间冲突
     */
    MEETING_ORDER_TIME_CONFLICT(502, "预约时间冲突"),
    /**
     * 会议室预约模块 - 会议室不可用
     */
    MEETING_ROOM_DISABLED(503, "会议室不可用"),
    /**
     * 会议室预约模块 - 可容纳人数不可大于65534
     */
    MEETING_ROOM_SIZE_OVERBIG(504, "会议室可容纳人数不可大于32767");

    //状态码
    private final Integer status;

    //对应的提示信息
    private final String msg;

    ResStatusEnum(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }

    /* getter */
    public Integer getStatus(){
        return this.status;
    }

    public String getMsg(){
        return this.msg;
    }
}
