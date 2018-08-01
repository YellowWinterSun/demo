package com.dayi.demo.scheduler;

import com.dayi.demo.service.MeetingOrderService;
import com.dayi.demo.service.RoleService;
import com.dayi.demo.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.util.Date;

/**
 * Quartz定时任务类：定期进行一些数据库处理
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/5
 */
public class DaoQuartz {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private MeetingOrderService meetingOrderService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UrlService urlService;

    //Log日志
    private static Logger logger = LoggerFactory.getLogger(DaoQuartz.class);

    /**
     * 定时频率：每半个小时
     * 任务描述：将过期的会议预约，状态从NORMAL改为OVERDUE
     */
    public void meetingOrderStatusOverdue(){
        logger.info("------------定时任务[半小时一次][处理所有过期的会议预约记录]------------");
        taskExecutor.execute(() -> {
            meetingOrderService.cleanOverdueOrder();
            logger.info("----------------- END OF 定时任务[半小时一次][处理所有过期的会议预约记录]");
        });
    }

    /**
     * 定时频率：一天一次
     * 任务描述：将所有取消的会议预约删除
     */
    public void deleteCancelOrder(){
        logger.info("------------定时任务[一天一次][清理所有当天取消的会议预约记录]------------");
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                meetingOrderService.deleteCancelOrder();
                logger.info("----------------- 定时任务[一天一次][清理所有当天取消的会议预约记录]");
            }
        });
    }

    /**
     * 定时频率：一周一次
     * 任务描述：将本周已完成的所有会议预约记录删除
     */
    public void deleteOverdueOrder(){
        logger.info("------------定时任务[一周一次][清理会议预约记录]------------");
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                meetingOrderService.deleteOverdueOrder();
                logger.info("----------------- 定时任务[一周一次][清理会议预约记录]");
            }
        });
    }

    /**
     * 定时频率：一天一次
     * 任务描述：清除系统的中间表无效数据
     */
    public void deleteVoidRecord(){
        logger.info("-----------定时任务[一天一次][清理中间表无效数据]--------");

        roleService.cleanNotExistsUserToRole();
        urlService.cleanVoidRecord();

        logger.info("-----------定时任务[一天一次][清理中间表无效数据]");
    }
}
