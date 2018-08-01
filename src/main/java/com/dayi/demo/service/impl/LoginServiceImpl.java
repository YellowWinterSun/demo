package com.dayi.demo.service.impl;

import com.dayi.demo.dao.UserDao;
import com.dayi.demo.model.UserExample;
import com.dayi.demo.service.LoginService;
import com.dayi.demo.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的描述
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    /**
     * 验证用户的手机号码和密码是否匹配
     *
     * @param phone    手机号码
     * @param password 密码
     * @return 匹配true，不匹配false
     */
    @Override
    public boolean validUser(String phone, String password) {

        //如果phone或者password存在空的情况，直接false返回
        if(StringUtils.isBlank(phone) || StringUtils.isBlank(password)){
            return false;
        }

        UserExample example = new UserExample();
        UserExample.Criteria c = example.createCriteria();
        c.andPhoneEqualTo(phone);
        c.andPasswordEqualTo(MD5Util.encode2hex(password));

        int num = userDao.countByExample(example);
        return (num == 1) ? true : false;
    }
}
