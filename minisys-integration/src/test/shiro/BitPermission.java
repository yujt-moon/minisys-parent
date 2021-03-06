package shiro;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.Permission;

/**
 * 规则
 *      +资源字符串+权限位+实例ID
 *
 * 以+开头 中间通过+分割
 *
 * 权限：
 *      0 表示所有权限
 *      1 新增 0001
 *      2 修改 0010
 *      4 删除 0100
 *      8 查看 1000
 *
 * 如 +user+10 表示对资源user拥有修改/查看权限
 *
 * 不考虑一些异常情况
 *
 * @author yujiangtao
 * @date 2018/4/28 9:38
 */
public class BitPermission implements Permission {

    private String resurceIdentify;

    private int permissionBit;

    private String instanceId;

    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");
        if(array.length > 1) {
            resurceIdentify = array[1];
        }
        if(StringUtils.isEmpty(resurceIdentify)) {
            resurceIdentify = "*";
        }

        if(array.length > 2) {
            permissionBit = Integer.valueOf(array[2]);
        }

        if(array.length > 3) {
            instanceId = array[3];
        }

        if(StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    public boolean implies(Permission permission) {
        if(!(permission instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) permission;

        if("*".equals(this.resurceIdentify) || this.resurceIdentify.equals(other.resurceIdentify)) {
            return false;
        }

        if(!(this.permissionBit == 0 ||(this.permissionBit & other.permissionBit) != 0)) {
            return false;
        }

        if(!("*".equals(this.instanceId)) || this.instanceId.equals(other.instanceId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resurceIdentify='" + resurceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
