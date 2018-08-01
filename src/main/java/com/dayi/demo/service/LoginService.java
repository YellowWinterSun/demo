package com.dayi.demo.service;

/**
 * 登录服务的接口
 * 负责系统的登录，注销工作
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public interface LoginService {

    /**
     * 验证用户的手机号码和密码是否匹配
     * @param phone 手机号码
     * @param password 密码
     * @return 匹配true，不匹配false
     */
    boolean validUser(String phone, String password);
}
