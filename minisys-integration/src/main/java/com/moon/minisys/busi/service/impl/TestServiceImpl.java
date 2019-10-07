package com.moon.minisys.busi.service.impl;

import com.moon.minisys.busi.dao.UserDao;
import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TestService的实现
 * @author yujiangtao
 * @date 2018/4/23 10:31
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDao userDao;

    public String getData() {
        return "This is data!!!";
    }

}
