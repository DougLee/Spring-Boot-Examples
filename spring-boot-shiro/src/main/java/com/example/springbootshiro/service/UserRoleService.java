package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.UserRole;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface UserRoleService extends IService<UserRole> {
    void deleteUserRoleByRoleId(String roleIds);

    void deleteUserRolesByUserId(String userIds);
}
