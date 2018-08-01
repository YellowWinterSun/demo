package com.dayi.demo.model.modelEnum;

/**
 * 绩效考核正表枚举类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/27
 */
public enum OfficialEvaluationEnum {
    /**
     * 初始化状态，考核人提交绩效考核表后的状态。（直属上司未审核）
     */
    INIT("INIT"),
    /**
     * 直属上司审核后，并评分确定后的状态。（部门负责人未审核）
     */
    BOSS1("BOSS1"),
    /**
     * 部门负责人审核后，并评分确定后的状态（HR未审核）
     */
    BOSS2("BOSS2"),
    /**
     * HR审核后的状态，此状态的表说明绩效考核表已完成，不可再改。
     */
    HR("HR");

    private final String value;

    OfficialEvaluationEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
