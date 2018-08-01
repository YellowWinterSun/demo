package com.dayi.demo.controller;

import com.dayi.demo.model.User;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.LoginService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    /**
     * 跳转登录界面（默认url)
     * @return
     */
    @RequestMapping("/")
    public String toLoginJsp(){
        return "login";
    }

    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping("/login")
    public String toLoginJsp2(){
        return "login";
    }

    /**
     * 跳转主页
     * @return
     */
    @RequestMapping("/index")
    public String toIndexJsp(){
        return "index";
    }

    @RequestMapping("/tohome")
    public String toUserManageJsp(){
        return "sys/userManage";
    }

    /**
     * 跳转开发者日记页面
     * @return
     */
    @RequestMapping("/dyTimeline")
    public String toDyTimelineJsp(){
        return "dyTimeline";
    }

    @RequestMapping("/aboutUsJsp")
    public String toAboutUsJsp(){
        return "aboutUs";
    }

    /**
     * 跳转404错误页面，找不到页面提示
     * @return
     */
    @RequestMapping("/to404")
    public String to404Jsp(){
        return "page404";
    }

    /**
     * 跳转HTTP状态码为500的服务器错误提示页面
     * @return
     */
    @RequestMapping("/to500")
    public String to500Jsp(){
        return "page500";
    }

    /**
     * 跳转“无权访问”提醒页面
     * @return
     */
    @RequestMapping("/to403")
    public String to403Jsp(){
        return "page403";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();

        return "logout";
    }

    /**
     * 登录验证控制器
     * @param phone 手机号码（登录名）
     * @param password 密码
     * @param session Session
     * @return 状态码100表示登录成功，其余表示失败，详情查看对应的提示信息
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Msg doLogin(String phone, String password, HttpSession session){

        //验证登录
        boolean isValid = loginService.validUser(phone, password);
        if(!isValid){
            //登录失败
            return Msg.res(ResStatusEnum.LOGIN_FAIL);
        }
        //登录成功
        //保存用户信息到session中
        session.removeAttribute("user");
        User user = userService.getUserByPhone(phone);
        session.setAttribute("user", user);

        return Msg.success("登录成功");
    }
}
