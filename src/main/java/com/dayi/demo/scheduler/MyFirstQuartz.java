package com.dayi.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

/**
 * quartz定时任务框架，第一个demo
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/15
 */
public class MyFirstQuartz {

    @Autowired
    private TaskExecutor taskExecutor;

    /**
     * 每5s执行一次的demo
     */
    public void mockMethod(){
        //System.out.println("定时任务demo");


    }
}
