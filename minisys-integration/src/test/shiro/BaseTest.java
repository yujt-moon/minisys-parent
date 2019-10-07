package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * @author yujiangtao
 * @date 2018/4/27 17:57
 */
public abstract class BaseTest {

    @After
    public void tearDown() throws Exception {
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile, String username, String password) {
        // 1、获取SecurityManager工厂，此处使用Init配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        // 2、得到SecurityManager实例并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 3、得到Subject及创建用户/密码身份验证Token（即用户名/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
