package com.moon.minisys.busi.controller;

import com.moon.minisys.util.WechatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信登录校验控制器
 * @author yujiangtao
 * @date 2018/3/24 19:54
 */
@Controller
@RequestMapping(value = "/wechat")
public class WechatValidController {

    @Value("${token}")
    private String token;

    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public void vadlid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if(WechatUtil.checkSignature(signature, token, timestamp, nonce)) {
            out.write(echostr);
        }
    }
}
