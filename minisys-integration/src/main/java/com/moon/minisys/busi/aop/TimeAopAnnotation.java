package com.moon.minisys.busi.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 时间统计的注解形式
 * @author yujiangtao
 * @date 2018/7/13 10:02
 */
@Component // 声明这是一个组件
@Aspect // 声明这是一个切面
public class TimeAopAnnotation {

    private final static Log log = LogFactory.getLog(TimeAopAnnotation.class);

    // 配置切入点，改方法无方法体，主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.moon.minisys.busi.service.impl.ItemServiceImpl.*(..))")
    public void aspect() {}

    /**
     * 配置前置通知，使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象，可以没有该参数
     * @param joinPoint
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        log.debug("执行before...");
    }

    /**
     * 配置后置通知，使用在方法aspect()上注册的切入点
     * @param joinPoint
     */
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        log.debug("执行after...");
    }

    // 配置环绕通知，使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if(log.isInfoEnabled()) {
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if(log.isInfoEnabled()) {
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            }
        }
        return result;
    }

    // 配置后置返回通知，使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturning(JoinPoint joinPoint) {
        if(log.isInfoEnabled()) {
            log.info("afterReturn " + joinPoint);
        }
    }

    // 配置抛出异常后通知，使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspect()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        if(log.isInfoEnabled()) {
            log.info("afterThrow " + joinPoint + "\t" + e.getMessage());
        }
    }
}
