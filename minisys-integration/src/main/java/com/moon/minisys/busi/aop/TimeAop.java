package com.moon.minisys.busi.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 计算方法调用时间的aop
 * @author yujiangtao
 * @date 2018/7/6 19:34
 */
public class TimeAop {

    private Log log = LogFactory.getLog(getClass());

    private Long startTime = null;

    public void beforeMethod() {
        startTime = System.currentTimeMillis();
    }

    public void afterMethod() {
        Long usedTime = System.currentTimeMillis() - startTime;
        log.debug("该方法共耗时：" + usedTime + "ms");
    }
}
