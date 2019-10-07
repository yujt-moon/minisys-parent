package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author yujiangtao
 * @date 2018/4/20 10:54
 */
public class MyRealm4 implements Realm {

    public String getName() {
        return "myRealm4";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        // 仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 得到用户名
        String username = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = (String) authenticationToken.getCredentials();
        if(!"zhang".equals(username)) {
            // 用户名错误
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)) {
            // 密码错误
            throw new IncorrectCredentialsException();
        }
        // 如果身份认证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
