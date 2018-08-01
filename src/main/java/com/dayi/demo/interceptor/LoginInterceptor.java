package com.dayi.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆验证拦截器
 * @author HuangDongYang<huangdy@pvc123.com></>
 * @date 2018-06-14
 */
public class LoginInterceptor implements HandlerInterceptor {

    //log日志处理
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 登陆拦截，未登录的用户会被拦截，并跳转登录页面
     * @param httpServletRequest HTTP请求对象
     * @param httpServletResponse HTTP响应对象
     * @param o
     * @return 是否拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取请求的url
        String url = httpServletRequest.getRequestURI();
        //System.out.println("url:" + url);

        //登录页面不拦截
        boolean isLoginUrl = url.equals("/") || url.equals("/login") || url.equals("/doLogin") || url.equals("/logout");
        if (isLoginUrl) {
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        if (null == session || null == session.getAttribute("user")){
            //logger.info("未登录，跳转登录界面");
            //重定向
            //httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            requestDirect(httpServletRequest, httpServletResponse, "/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //System.out.println("HandlerInterceptor1......postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println("HandlerInterceptor1......afterCompletion");
    }

    /**
     * 判断是否是ajax请求
     * @param request HTTP请求
     * @param response HTTP响应
     * @return true表示是Ajax的HTTP请求。false则反之
     */
    private boolean ajaxDofilterSessionNull(HttpServletRequest request, HttpServletResponse response){
        boolean isAjax = false;
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            // ajax请求
            response.setHeader("sessionstatus", "timeout");
            isAjax = true;
        }
        return isAjax;
    }

    /**
     * 重定向封装方法
     * @param request HTTP请求
     * @param response HTTP响应
     * @param redirectPath 要重定向的路径
     * @throws IOException
     */
    public void requestDirect(HttpServletRequest request, HttpServletResponse response, String redirectPath) throws IOException {
        //如果是ajax请求，则设置重定向内容
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //前端需要判断是否是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //需要重定向的路径
            response.setHeader("CONTENTPATH",request.getContextPath() + redirectPath);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.sendRedirect(request.getContextPath() + redirectPath);
        }
    }
}
