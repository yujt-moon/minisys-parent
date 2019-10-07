package com.moon.minisys.shiro;

import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.UserService;
import com.moon.minisys.exception.IncorrectUsernameOrPasswordException;
import com.moon.minisys.util.spring.SpringContextHolder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2018/4/30 9:21
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "userRealm";
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authenticationToken.getCredentials());

        User user = userService.findByUsername(username);
        if(user == null) {
            // 没找到账号
            throw new UnknownAccountException();
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
            // 账号锁定
            throw new LockedAccountException();
        }

        // 交给AuthernticatingRealm使用CredentialsMatcher进行密码匹配，
        // 如果觉得人家的不好，可以自定义实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username,
                password,
                // salt = username + salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return info;
    }
}
