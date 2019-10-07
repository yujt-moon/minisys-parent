package com.moon.minisys.busi.service;

import com.moon.minisys.busi.pojo.Permission;

/**
 * @author yujiangtao
 * @date 2019/7/1 10:10
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
