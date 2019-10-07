package com.moon.minisys.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存Spring ApplicationContext，
 * 可在任何代码任何时候中取出ApplicationContext
 * @author yujiangtao
 * @date 2018/4/30 16:07
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的context注入函数，
     * 将其存入静态变量
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean，
     * 自动转型为所赋值对象的类型
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 如果有多个Bean符合Class, 取出第一个.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBeansOfType(clazz);
    }

    private static void checkApplicationContext() {
        if(applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入," +
                    "请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
