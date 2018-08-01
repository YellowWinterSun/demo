package com.dayi.demo.model.modelEnum;

/**
 * System_url表的status字段枚举
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
public enum UrlStatusEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL"),
    /**
     * 不可用
     */
    DISABLED("DISABLED");

    private final String value;

    UrlStatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
