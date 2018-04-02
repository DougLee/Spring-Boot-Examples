package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.domain.RoleMenu;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("roleMenuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends BaseService<RoleMenu> implements RoleMenuService {

    @Override
    public void deleteRoleMenusByRoleId(String roleIds) {

    }

    @Override
    public void deleteRoleMenusByMenuId(String menuIds) {

    }
}
