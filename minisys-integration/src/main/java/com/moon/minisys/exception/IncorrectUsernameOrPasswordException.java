package com.moon.minisys.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 用户名或者密码错误
 * @author yujiangtao
 * @date 2019/6/28 11:36
 */
public class IncorrectUsernameOrPasswordException extends AuthenticationException {

    public IncorrectUsernameOrPasswordException() {
        super();
    }

    public IncorrectUsernameOrPasswordException(String msg) {
        super(msg);
    }

    public IncorrectUsernameOrPasswordException(Throwable t) {
        super(t);
    }

    public IncorrectUsernameOrPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
}
