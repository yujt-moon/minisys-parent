package com.moon.minisys.annotation;

import java.lang.annotation.*;

/**
 * 需要导出到excel中的字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelField {

    /**
     * excel中使用的字段中文名
     * @return
     */
    String name() default "";

    /**
     * 日期的格式化字符串
     * @return
     */
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";
}
