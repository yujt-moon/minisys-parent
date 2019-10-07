package shiro;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2018/4/28 11:05
 */
public class AuthorizerTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        login("classpath:shiro/shiro-authorizer.ini", "zhang", "123");
        // 判断拥有权限：user:create
//        Assert.assertTrue(subject().isPermitted("user1:create"));
//        Assert.assertTrue(subject().isPermitted("user2:create"));
        // 通过二进制位的方式表示权限
        // 新增权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));
        // 查看权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));
        // 新增及查看
        Assert.assertTrue(subject().isPermitted("+user1+10"));

        // 没有删除权限
        Assert.assertFalse(subject().isPermitted("+user1+4"));

        // 通过MyRolePermissionResolver解析得到的权限
        Assert.assertTrue(subject().isPermitted("menu:view"));
    }
}
