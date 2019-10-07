package shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2018/4/27 20:01
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        login("classpath:shiro/shiro-permission.ini", "zhang", "123");
        // 判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        // 判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        // 判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:shiro/shiro-permission.ini", "zhang", "123");
        // 断言拥有权限：user:create
        subject().checkPermission("user:create");
        // 断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        // 断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }
}
