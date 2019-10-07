package com.moon.minisys.busi.controller;

import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试控制器
 * @author yujiangtao
 * @date 2018/4/23 10:36
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getData")
    @ResponseBody
    public String getData() {
        return testService.getData();
    }

    /**
     * freemarker框架搭建测试
     * @param model
     * @return
     */
    @RequestMapping(value = "/freemarker")
    public String testFreeMarker(Model model) {
        List<User> users = new ArrayList<User>();
        List<User> emptyList = new ArrayList<User>();
        users.add(new User("张三", "123456"));
        users.add(new User("李四", "123457"));
        users.add(new User("王五", "123458"));
        users.add(new User("麻子", "123459"));

        model.addAttribute("emptyList", emptyList);
        model.addAttribute("users", users);
        model.addAttribute("user", new User("john", "123456"));
        model.addAttribute("msg", "Hello World!");
        return "helloWorld";
    }
}
