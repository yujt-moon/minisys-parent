package com.moon.minisys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author yujiangtao
 * @since 1.0
 */
public class TimeUtils {

    /**
     * 根据格式化字符串，格式化日期
     * @param date 日期
     * @param format 格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 格式化当前日期
     * @param format 格式化字符串
     * @return
     */
    public static String formatCurrentDate(String format) {
        return formatDate(new Date(), format);
    }
}
