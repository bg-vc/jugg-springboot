package com.jugg.vince.springboot.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author:   Vince
 * Date:     2018/7/25 上午10:26
 * Description:
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_HOUR_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_YEAR_MONTH = "yyyy-MM";
    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String YYYYMMDDMMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDMMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 按指定格式将字符串转换为日期
     *
     * @param dateStr 日期串
     * @param pattern 格式
     * @return 日期
     * @throws Exception 异常
     */
    public static Date str2Date(String dateStr, String pattern) throws Exception {
        if (null == dateStr) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.parse(dateStr);
    }

    /**
     * 按指定格式将字符串转换为日期时间
     *
     * @param dateStr 日期串
     * @param pattern 格式
     * @return 日期时间
     * @throws ParseException 解析异常
     */
    public static Date str2DateTime(String dateStr, String pattern) {
        if (null == dateStr) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将日期格式化为字符串
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化后的日期串
     */
    public static String date2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        } else {
            if (null == pattern) {
                pattern = DEFAULT_DATE_FORMAT;
            }
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern(pattern);
            return format.format(date);
        }
    }

    /**
     * 将日期时间格式化为字符串
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化后的日期时间串
     */
    public static String dateTime2Str(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        if (null == pattern) {
            pattern = DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(pattern);
        return format.format(date);
    }

    /**
     * 取得当前时间戳
     *
     * @return 当前时间戳
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 取得当前日期
     *
     * @return 当前日期
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 取得今天的最小时间
     *
     * @return 今天的最小时间
     */
    public static Date getTodayMin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 取得今天的最大时间
     *
     * @return 今天的最大时间
     */
    public static Date getTodayMax() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * 取得当前时间戳格式为：pattern
     *
     * @return 当前时间戳
     */
    public static String getCurrentTime(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

}