package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.Role;
import org.apache.ibatis.annotations.Param;

/**
 * @author yujiangtao
 * @date 2019/7/2 8:34
 */
public interface RoleDao {

    int createRole(@Param("role") final Role role);

    int deleteRole(@Param("roleId") Long roleId);
}
