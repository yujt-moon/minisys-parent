package com.moon.minisys.busi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moon.minisys.busi.dao.UserDao;
import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.UserService;
import com.moon.minisys.pojo.Limit;
import com.moon.minisys.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用户 ServiceImpl
 * @author yujiangtao
 * @date 2018/4/30 15:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User createUser(User user) {
        // 加密密码
        passwordHelper.encryPassword(user);
        userDao.createUser(user);
        return user;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryPassword(user);
        userDao.updateUser(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }

    @Override
    public PageInfo<User> getAllUsers(Limit limit) {
        PageHelper.startPage(limit.getPageNum(), limit.getPageSize());
        List<User> allUsers = userDao.getAllUsers();
        PageInfo<User> pageInfo = new PageInfo<>(allUsers);
        return pageInfo;
    }
}
