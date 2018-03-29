package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.RoleMenu;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String roleIds);

    void deleteRoleMenusByMenuId(String menuIds);
}
