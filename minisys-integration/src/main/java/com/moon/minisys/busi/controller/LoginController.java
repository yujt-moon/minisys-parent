package com.moon.minisys.busi.controller;

import com.moon.minisys.constant.Constant;
import com.moon.minisys.constant.SeparatorConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录控制器
 * @author yujiangtao
 * @date 2018/2/28 18:40
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("/toLoginPage")
    public String toLoginPage() {
        return "page/login";
    }

    /**
     * 登陆方法
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        logger.info(SeparatorConstant.LOG_PREFIX_HYPHEN + "login start...");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 默认成功
        String code = Constant.SUCCESS_CODE;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error(e.toString());
            code = Constant.ERROR_CODE;
            model.addAttribute("errorMsg", "用户名错误！");
        } catch (IncorrectCredentialsException e) {
            logger.error(e.toString());
            code = Constant.ERROR_CODE;
            model.addAttribute("errorMsg", "密码错误！");
        }
        logger.info(SeparatorConstant.LOG_PREFIX_HYPHEN + "login end...");
        // 登陆成功跳转首页
        if(code.equals(Constant.SUCCESS_CODE)) {
            return "forward:/index/toMenu";
        } else {
            // 登陆失败跳转登陆页面
            return "forward:/login/toLoginPage";
        }
    }

    @RequestMapping(value = "/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
