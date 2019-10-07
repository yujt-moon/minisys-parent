package com.moon.minisys.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 反射工具类
 */
public class ReflectUtil {

    /**
     * 获取字段数组中，被指定注解的字段数组子集
     * @param fields
     * @param annotationClass 注解类型
     * @return
     */
    public static Field[] getAnnotatedFields(Field[] fields, Class annotationClass) {
        if(fields == null || fields.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Annotation annotation = field.getDeclaredAnnotation(annotationClass);
            if(null == annotation) {
                // 剔除该字段
                Field[] temp = new Field[fields.length - 1];
                System.arraycopy(fields, 0, temp, 0, i);
                System.arraycopy(fields, i + 1, temp, i, temp.length - i);
                fields = temp;
                i--;
            }
        }
        System.out.println("=======" + Arrays.toString(fields) + "========");
        return fields;
    }
}
