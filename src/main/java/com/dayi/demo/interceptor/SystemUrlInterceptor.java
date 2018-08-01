package com.dayi.demo.interceptor;

import com.dayi.demo.dao.SystemUrlDao;
import com.dayi.demo.model.SystemUrl;
import com.dayi.demo.model.User;
import com.dayi.demo.model.modelEnum.UrlStatusEnum;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限验证拦截器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/19
 */
public class SystemUrlInterceptor implements HandlerInterceptor {

    @Autowired
    private UrlService urlService;
    @Autowired
    private RoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        //获取请求的url
        String url = httpServletRequest.getRequestURI();

        SystemUrl systemUrl = urlService.getUrlByUrl(url);
        if(null == systemUrl){
            //说明当前url不在校验范围内
            return true;
        }

        //判断当前URL是否可用，status=NORMAL
        if (systemUrl.getStatus().equals(UrlStatusEnum.DISABLED.getValue())){
            //当前URL不可用
            //httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/to403");
            requestDirect(httpServletRequest, httpServletResponse, "/to403");
            return false;
        }

        //判断当前用户是否拥有当前权限
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        boolean valid = roleService.validUserUrl(user.getNo(), systemUrl.getUrl());
        if (!valid){
            //当前用户不允许访问当前URL
            //httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/to403");
            requestDirect(httpServletRequest, httpServletResponse, "/to403");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) throws Exception {

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
