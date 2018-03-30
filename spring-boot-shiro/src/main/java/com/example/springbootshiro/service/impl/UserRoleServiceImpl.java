package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.domain.UserRole;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Douglee on 2018/3/30.
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUserRoleByRoleId(String roleIds) {

        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", UserRole.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUserRolesByUserId(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.batchDelete(list, "userId", UserRole.class);
    }

}
