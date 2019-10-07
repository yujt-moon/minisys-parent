package com.moon.minisys.busi.controller;

import com.github.pagehelper.PageInfo;
import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.UserService;
import com.moon.minisys.pojo.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 * @author yujiangtao
 * @date 2018/5/3 16:21
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户服务类
     */
    @Autowired
    private UserService userService;

    /**
     * 跳转用户列表页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String userList(Limit limit, HttpServletRequest request, Model model) {
        PageInfo<User> allUsers = userService.getAllUsers(limit);
        model.addAttribute("page", allUsers);
        return "page/user/userList";
    }
}
