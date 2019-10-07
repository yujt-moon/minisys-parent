package shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author yujiangtao
 * @date 2018/4/28 10:32
 */
public class BitAndWildPermissionResolver implements PermissionResolver {

    public Permission resolvePermission(String s) {
        if(s.startsWith("+")) {
            return new BitPermission(s);
        }
        return new WildcardPermission(s);
    }
}
