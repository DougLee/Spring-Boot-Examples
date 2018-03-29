package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.RoleInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface RoleService extends IService<RoleInfo> {
    List<RoleInfo> findUserRole(String usreName);

    List<RoleInfo> findAllRole(RoleInfo roleInfo);

    RoleInfo findByName(String roleName);

    void addRole(RoleInfo roleInfo, Long[] menuIds);

    void updateRole(RoleInfo roleInfo, Long[] menuIds);
    void deleteRoles(String roleIds);
}
