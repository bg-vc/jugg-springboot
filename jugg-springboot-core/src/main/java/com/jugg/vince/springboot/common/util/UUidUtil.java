package com.jugg.vince.springboot.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * Author:   Vince
 * Date:     2018/7/25 上午10:52
 * Description:
 */
public class UUidUtil {

    public static String getUUid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid.length()>30? uuid.substring(0,30):uuid;

    }

    public static String get16UUid() {
        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(getUUid());
        System.out.println(get16UUid());
    }
}

