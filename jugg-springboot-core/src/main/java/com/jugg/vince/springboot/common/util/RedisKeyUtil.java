package com.jugg.vince.springboot.common.util;

/**
 * Author:   Vince
 * Date:     2018/7/25 上午10:24
 * Description:
 */
public class RedisKeyUtil {
    public static String KEY = "jugg.vince.springboot.";

    public static String getKey(String biz, String str) {
        return KEY + biz + str;
    }
}
