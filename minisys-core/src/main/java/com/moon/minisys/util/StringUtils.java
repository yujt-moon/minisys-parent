package com.moon.minisys.util;

import com.moon.minisys.constant.FilePrefixStrategy;

import java.util.UUID;

/**
 * 字符串工具类
 * @author yujiangtao
 * @since 1.0
 */
public class StringUtils {

    /**
     * 判断当前字符串是否是空串或者为空
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断当前字符串是否不是空串或者为空
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * 根据策略类型生成文件前缀
     * @param strategy 文件前缀策略枚举类型
     * @return
     */
    public static String generateFilePrefix(FilePrefixStrategy strategy) {
        String prefixStr = "";
        switch (strategy) {
            case DAYS:
                prefixStr = TimeUtils.formatCurrentDate("yyyyMMddHHmm");
                break;
            case UUID:
                prefixStr = UUID.randomUUID().toString();
                break;
            default:
                break;
        }
        return prefixStr;
    }
}
