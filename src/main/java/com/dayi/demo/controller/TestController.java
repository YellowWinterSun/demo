package com.dayi.demo.controller;

import com.dayi.demo.model.User;
import com.dayi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 只是个测试用的控制器，请忽略注释，代码规范，在项目完工后会删除该类
 */
@Controller
@RequestMapping("/testController")
public class TestController {

//    @Autowired
//    UserMapper userMapper;
//

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String toTest(){
        return "test/test";
    }
//
//    static Logger logger = LoggerFactory.getLogger(TestController.class);
//
//    @RequestMapping("/testLog")
//    @ResponseBody
//    public String testLog(){
//
//        logger.debug("debug输出");
//        logger.info("info输出");
//        logger.warn("warn输出");
//        logger.error("error输出");
//        studentMapper.updateByPrimaryKey(new Student());
//        return "log-ok";
//    }
//
//    @RequestMapping("/getDate")
//    @ResponseBody
//    public Msg getDate(){
//        UserExample userExample = new UserExample();
//        UserExample.Criteria c = userExample.createCriteria();
//        c.andNameEqualTo("黄冬阳");
//
//        User user = userMapper.selectByExample(userExample).get(0);
//        System.out.println(user.getName());
//
//        //测试Date
//        Date entryDate = user.getEntrydate();
//        System.out.println(entryDate);
//
//        Date createDate = user.getCreatetime();
//        System.out.println(createDate);
//
//        return Msg.success(null).add("user", user).add("date1", entryDate).add("date2", createDate);
//    }

    @RequestMapping("/testJson")
    @ResponseBody
    public User testJson(){
        List<User> list = userService.listUser();
        return list.get(0);
    }

    @RequestMapping("/testLogger")
    @ResponseBody
    public String testLogger(){
        Integer i = Integer.parseInt("ab2");
        return "testLogger";
    }

    @RequestMapping("/testDateString")
    @ResponseBody
    public Date testDateString(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("date:" + format.format(date));
        return date;
    }

    @RequestMapping("/testDatetimeString")
    @ResponseBody
    public Date testDatetimeString(Date datetime){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("datetime:" + format.format(datetime));
        return datetime;
    }

    @InitBinder("date")
    public void initBinderDate(ServletRequestDataBinder bin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }

    @InitBinder("datetime")
    public void initBinderDatetime(ServletRequestDataBinder bin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }
}

