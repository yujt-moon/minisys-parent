package com.moon.minisys.busi.service;

import com.moon.minisys.busi.pojo.Role;

/**
 * @author yujiangtao
 * @date 2019/7/1 10:14
 */
public interface RoleService {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间的关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间的关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
