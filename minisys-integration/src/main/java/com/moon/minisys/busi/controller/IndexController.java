package com.moon.minisys.busi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 框架搭建测试 Controller
 * @author yujiangtao
 * @date 2018/2/23 9:52
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    /**
     * 日志对象
     */
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 未配置视图解析器的时候（TODO 已无法访问）
     * @return
     */
    @RequestMapping("/toMenuTwo")
    public String toMenuPage() {
        return "/WEB-INF/jsp/page/menu.jsp";
    }

    /**
     * 配置视图解析器
     * @return
     */
    @RequestMapping("/toMenu")
    public String toMenuPageTwo() {
        logger.debug("调试日志。。。");
        return "page/menu";
    }
}
