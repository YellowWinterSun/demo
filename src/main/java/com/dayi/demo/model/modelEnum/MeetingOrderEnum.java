package com.dayi.demo.model.modelEnum;

/**
 * 会议室预约状态枚举类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
public enum MeetingOrderEnum {
    /**
     * 正常（预约成功，直到会议结束前）
     */
    NORMAL("NORMAL"),
    /**
     * 结束，过期（会议预约成功后，到达约定结束时间后）
     */
    OVERDUE("OVERDUE"),
    /**
     * 取消（预约成功后，未进行会议前取消）
     */
    CANCEL("CANCEL");

    private final String value;

    MeetingOrderEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
