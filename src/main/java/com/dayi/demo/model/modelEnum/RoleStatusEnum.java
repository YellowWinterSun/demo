package com.dayi.demo.model.modelEnum;

/**
 * 角色状态枚举
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
public enum RoleStatusEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL"),
    /**
     * 不可用
     */
    DISABLED("DISABLED");

    private final String value;

    RoleStatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
