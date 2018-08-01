package com.dayi.demo.util;

import java.util.UUID;

/**
 * 生成uuid的工具类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/14
 */
public class UUIDUtil {

    /**
     * 生成32位不带"-"符号的UUID
     * @return 32位uuid字符串
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
