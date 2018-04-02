package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.RoleMapper;
import com.example.springbootshiro.domain.RoleInfo;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<RoleInfo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<RoleInfo> findUserRole(String userName) {
        return this.roleMapper.findUserRole(userName);
    }

    @Override
    public List<RoleInfo> findAllRole(RoleInfo roleInfo) {
        return null;
    }

    @Override
    public RoleInfo findByName(String roleName) {
        return null;
    }

    @Override
    public void addRole(RoleInfo roleInfo, Long[] menuIds) {

    }

    @Override
    public void updateRole(RoleInfo roleInfo, Long[] menuIds) {

    }

    @Override
    public void deleteRoles(String roleIds) {

    }
}
