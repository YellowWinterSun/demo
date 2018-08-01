package com.dayi.demo.interceptor;

import com.dayi.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

/**
 * 日志处理拦截器，主要记录系统响应用户请求的性能数据
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/11
 */
public class LoggerInterceptor implements HandlerInterceptor {

    //log日志处理
    private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
    //记录进入该拦截器，当前线程的时间
    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal startTime");
    //记录当前线程从请求进入到处理结束，整个运行过程的信息，并在最后打印到logger日志中，防止多线程并发进行的时候，logger日志打印被拆开不容易读
    private static final ThreadLocal<StringBuilder> sbThreadLocal = new NamedThreadLocal<>("ThreadLocal logText");

    /**
     * HTTP请求，进入Controller方法前。记录响应请求前的开始时间，控制器，方法，传入参数和URL
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //记录开始时间
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        //如果已登录，则记录操作的用户工号
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("---------开始计时:").append(new SimpleDateFormat("hh:mm:ss.SSS").format(startTime)).append("------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            if (null != user) {
                sb.append("用户工号   : ").append(user.getNo()).append("\n");
            }
            sb.append("控制器     : ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("控制器方法 : ").append(h.getMethod().getName()).append("\n");
            sb.append("传入参数   : ").append(getParamString(httpServletRequest.getParameterMap())).append("\n");
            sb.append("URI        : ").append(httpServletRequest.getRequestURI()).append("\n");

            sbThreadLocal.set(sb);
        }

        return true;
    }

    /**
     * 响应Controller方法后，在进行View视图渲染前。记录控制器处理完请求所消耗的时间
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if(handler instanceof HandlerMethod) {
            StringBuilder sb = sbThreadLocal.get();
            sb.append("处理请求耗时  : ").append(executeTime).append("ms").append("\n");
        }
    }

    /**
     * 整个HTTP请求处理结束之后。记录整个请求处理完所消耗的时间，内存情况。如果有异常，则会记录本次请求的报错信息
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) throws Exception {

            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis(); 	//2、结束时间

            //如果controller报错，则记录异常错误
            if(ex != null){
                logger.error("Controller抛出异常: " + getStackTraceAsString(ex));
            }

            StringBuilder sb = sbThreadLocal.get();
            sb.append("计时结束："+ new SimpleDateFormat("hh:mm:ss.SSS").format(endTime) + " 处理整个HTTP请求耗时：" +
                    new SimpleDateFormat(".SSS").format(endTime - beginTime) + "ms  URI:" +
                    httpServletRequest.getRequestURI()+ " 最大内存: " +Runtime.getRuntime().maxMemory()/1024/1024 + "m  已分配内存: " +
                    Runtime.getRuntime().totalMemory()/1024/1024+ "m  已分配内存中的剩余空间: " +Runtime.getRuntime().freeMemory()/1024/1024+ "m  最大可用内存: " +
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024 + "m");
            sb.append("\n=====================================================================\n");

            logger.info(sb.toString());

            startTimeThreadLocal.remove();
            sbThreadLocal.remove();
    }

    /**
     * 获取参数并解析成可读字符串
     *
     * @param map
     * @return
     */
    private String getParamString(Map<String, String[]> map) {
        if (null == map){
            return " ";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
