package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.domain.RoleMenu;
import com.example.springbootshiro.domain.UserRole;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("roleMenuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends BaseService<RoleMenu> implements RoleMenuService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRoleMenusByRoleId(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", RoleMenu.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRoleMenusByMenuId(String menuIds) {
        List<String> list = Arrays.asList(menuIds.split(","));
        this.batchDelete(list, "menuId", RoleMenu.class);

    }
}
