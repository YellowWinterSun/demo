package com.dayi.demo.scheduler;

import com.dayi.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.util.Date;

/**
 * Quartz定时任务类：通知会议的定时任务类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/4
 */
public class MessageQuartz {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private MessageService messageService;

    /**
     * 定时频率：每5分钟执行一次
     * 任务描述：前1小时，30分钟通知一次用户参与会议。
     */
    public void messageMeetingStart(){
        Date date = new Date();
        System.out.println("定义任务启动，现在的时间:" + date);
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                messageService.informMeetingOrderStartTask();
            }
        });
    }
}
