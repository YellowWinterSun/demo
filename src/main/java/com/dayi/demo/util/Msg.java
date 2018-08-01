package com.dayi.demo.util;

import com.dayi.demo.msgEnum.ResStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回体
 *
 * @author HuangDongYang<huangdy@pvc123.com>
 * @date 2018/6/14
 */
public class Msg {
    // 状态码，100表示成功，200表示失败
    private int code;

    // 返回提示信息
    private String msg;

    // 携带的信息
    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 默认成功返回，传入自定义提示信息
     * @param returnMsg 提示信息
     * @return 构造成功的Msg对象
     */
    public static Msg success(String returnMsg) {
        Msg msg = new Msg();
        msg.setCode(100);

        if (null != returnMsg){
            msg.setMsg(returnMsg);
            return msg;
        }
        msg.setMsg("");

        return msg;
    }

    /**
     * 默认失败返回，传入自定义提示信息
     * @param returnMsg 提示信息
     * @return 构造失败的Msg对象
     */
    public static Msg error(String returnMsg) {
        Msg msg = new Msg();
        msg.setCode(200);

        if (null != returnMsg){
            msg.setMsg(returnMsg);
            return msg;
        }
        msg.setMsg("");

        return msg;
    }

    /**
     * 利用状态码枚举类构建Msg
     * @param RES 状态码枚举变量
     * @return 特殊的Msg对象
     */
    public static Msg res(ResStatusEnum res){
        Msg msg = new Msg();
        msg.setCode(res.getStatus());
        msg.setMsg(res.getMsg());

        return msg;
    }

    /**
     * 添加携带的对象信息
     * @param key 键名，字符串类型
     * @param value 值，Object类型
     * @return
     */
    public Msg add(String key, Object value) {
        map.put(key, value);

        return this;
    }

    // getter-setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
