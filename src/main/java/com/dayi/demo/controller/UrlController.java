package com.dayi.demo.controller;

import com.dayi.demo.model.User;
import com.dayi.demo.msgEnum.ResStatusEnum;
import com.dayi.demo.service.UrlService;
import com.dayi.demo.service.UserService;
import com.dayi.demo.util.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 权限控制 - 控制器
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/7/9
 */
@Controller
@RequestMapping("/sys/url")
public class UrlController {

    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;

    //记录系统中不可禁用的权限
    final HashSet<String> defaultUrlSet = new HashSet<String>(){{
        add("b3cab687a3a94e24a4cf606d571f5575");    //  /sys/url
        add("5c4d1af0798e476cb7500e9fbb170e04");    //  /sys/url/changeUrlStatus
        add("d21040d1d72740fe8acc3a142c9b57c7");    //  /sys/url/listUrl
    }};

    @RequestMapping
    public String toIndex(){
        return "sys/urlManage";
    }

    /**
     * 根据条件获取权限信息
     * @return
     */
    @RequestMapping("listUrl")
    @ResponseBody
    public Msg listUrl(String id, String url, Integer limitStart, Integer limitEnd, String order, String sort){
        //校验参数
        if (StringUtils.isNotBlank(id)){
            //传入了id，url条件将失效
            url = null;
        }

        Map<String, Object> map = urlService.listUrlRes(id, url, limitStart, limitEnd, order, sort);

        return Msg.success(null).add("list", map.get("list"))
                .add("total", map.get("total"))
                .add("rows", map.get("rows"));
    }

    /**
     * 更改权限的状态
     * @param urlIds 要更改的权限id集合字符串（多个权限id用逗号隔开)
     * @return
     */
    @RequestMapping("changeUrlStatus")
    @ResponseBody
    public Msg changeUrlStatus(String urlIds){
        if (StringUtils.isBlank(urlIds)){
            return Msg.res(ResStatusEnum.DATA_NULL);
        }

        //解析字符串
        List<String> listIds = new ArrayList<>();
        for (String urlId : urlIds.split(",")){
            if (defaultUrlSet.contains(urlId)){
                //权限控制的所有操作URL不可禁用，因为禁用了，整个系统会处于一个无法控制权限的状态
                return Msg.res(ResStatusEnum.URL_DEFAULT_CANNOT_DISABLED);
            }

            listIds.add(urlId);
        }

        urlService.updateUrlStatus(listIds);
        return Msg.success(null);
    }

    /**
     * 获取当前用户可用的权限列表
     * @param session
     * @return
     */
    @RequestMapping("/listMyEnableUrl")
    @ResponseBody
    public List<String> listMyEnableUrl(HttpSession session){
        User user = (User) session.getAttribute("user");

        List<String> list = urlService.listUserEnableUrl(user.getId());

        return list;
    }
}
