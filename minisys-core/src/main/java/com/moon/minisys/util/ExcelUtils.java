package com.moon.minisys.util;

import com.moon.minisys.annotation.ExcelField;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel（csv）导入导出工具类
 */
public class ExcelUtils {

    /**
     * 导出cvs
     * @param response
     * @param iterable
     */
    public static void exportCVS(HttpServletResponse response, String fileName, Iterable iterable) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/octet-stream");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        Iterator iterator = iterable.iterator();
        String[] titles = null;
        Method[] methods = null;
        // 如果还有
        if(iterator.hasNext()) {
            Object next = iterator.next();
            Class<?> aClass = next.getClass();
            Field[] fields = aClass.getDeclaredFields();
            fields = ReflectUtil.getAnnotatedFields(fields, ExcelField.class);
            titles = new String[fields.length];
            methods = new Method[fields.length];
            if(fields != null && fields.length > 0) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    titles[i] = field.getDeclaredAnnotation(ExcelField.class).name();
                    try {
                        Method method = aClass.getMethod("get" + field.getName().substring(0, 1).toUpperCase() +
                                field.getName().substring(1));
                        methods[i] = method;
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            Iterable records = generateRecords(iterable, methods, fields);
            printCSV(response, titles, records);
        }
    }

    /**
     * 获取对应的对象信息并处理
     * @param iterable
     * @param methods
     * @param fields
     * @return
     */
    private static Iterable generateRecords(Iterable iterable, Method[] methods, Field[] fields) {
        List<String[]> records = new ArrayList<>();
        Iterator iterator = iterable.iterator();
        Object next;
        while(iterator.hasNext()) {
            next = iterator.next();
            String[] record = new String[methods.length];
            Object invoke = null;
            for (int i = 0; i < methods.length; i++) {
                try {
                    invoke = methods[i].invoke(next);
                }  catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(invoke == null) {
                    record[i] = null;
                } else {
                    // 如果是date类型，就格式化
                    if(invoke instanceof Date) {
                        ExcelField excelField = fields[i].getDeclaredAnnotation(ExcelField.class);
                        String dateFmt = excelField.dateFormat();
                        if(StringUtils.isBlank(dateFmt)) {
                            dateFmt = "yyyy-MM-dd HH:mm:ss";
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
                        record[i] = sdf.format((Date)invoke);
                    } else {
                        record[i] = invoke.toString();
                    }
                }
            }
            records.add(record);
        }
        return records;
    }

    /**
     * 输出csv文件
     * @param response
     * @param titles
     * @param records
     */
    private static void printCSV(HttpServletResponse response, String[] titles, Iterable records) {
        CSVPrinter printer = null;
        CSVFormat format = CSVFormat.DEFAULT.withHeader(titles);
        try {
            printer = new CSVPrinter(response.getWriter(), format);
            printer.printRecords(records);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                printer.flush();
                printer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
