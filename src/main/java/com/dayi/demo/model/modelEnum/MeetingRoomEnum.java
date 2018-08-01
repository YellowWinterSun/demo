package com.dayi.demo.model.modelEnum;

/**
 * 会议室状态枚举类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
public enum MeetingRoomEnum {
    /**
     * 正常
     */
    NORMAL("NORMAL"),
    /**
     * 不可用
     */
    DISABLED("DISABLED");

    private final String value;

    MeetingRoomEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
