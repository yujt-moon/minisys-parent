package com.moon.minisys.busi.dao;

import com.moon.minisys.busi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户 Dao
 * @author yujiangtao
 * @date 2018/4/23 11:13
 */
public interface UserDao {

    /**
     * 创建用户
     * @param user
     * @return
     */
    int createUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int deleteUser(@Param("userId") Long userId);

    int correlationRoles(@Param("userId") Long userId,
                         @Param("roleIds") Long... roleIds);

    int uncorrelationRoles(@Param("userId") Long userId,
                           @Param("roleIds") Long... roleIds);

    int exists(@Param("userId") Long userId, @Param("roleId") Long roleId);

    User findOne(@Param("userId") Long userId);

    User findByUsername(@Param("username") String username);

    Set<String> findRoles(@Param("username") String username);

    Set<String> findPermissions(@Param("username") String username);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> getAllUsers();
}
