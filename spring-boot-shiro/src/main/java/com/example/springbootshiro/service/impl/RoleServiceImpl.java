package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.RoleMapper;
import com.example.springbootshiro.dao.RoleMenuMapper;
import com.example.springbootshiro.domain.RoleInfo;
import com.example.springbootshiro.domain.RoleMenu;
import com.example.springbootshiro.domain.UserRole;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.RoleMenuService;
import com.example.springbootshiro.service.RoleService;
import com.example.springbootshiro.service.UserRoleService;
import com.example.springbootshiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<RoleInfo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<RoleInfo> findUserRole(String userName) {
        return this.roleMapper.findUserRole(userName);
    }

    @Override
    public List<RoleInfo> findAllRole(RoleInfo roleInfo) {
        Example example = new Example(RoleInfo.class);
        if (!StringUtils.isNullorEmpty(roleInfo.getRoleName())){
            example.createCriteria().andCondition("role_name=", roleInfo.getRoleName());
        }
        example.setOrderByClause("create_time");
        return selectByExample(example);
    }

    @Override
    public RoleInfo findByName(String roleName) {
        Example example = new Example(RoleInfo.class);
        example.createCriteria().andCondition("lower(role_name)=", roleName.toLowerCase());
        List<RoleInfo> list = this.roleMapper.selectByExample(example);
        if (list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addRole(RoleInfo roleInfo, Long[] menuIds) {
        roleInfo.setCreateTime(new Date());
        this.save(roleInfo);
        for (Long menuId: menuIds){
            RoleMenu roleMenu = new RoleMenu();

            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleInfo.getRoleId());

            this.roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateRole(RoleInfo roleInfo, Long[] menuIds) {
        roleInfo.setModifyTime(new Date());
        this.roleMapper.updateByPrimaryKeySelective(roleInfo);
        Example example = new Example(RoleMenu.class);
        example.createCriteria().andCondition("role_id=", roleInfo.getRoleId());
        this.roleMenuMapper.deleteByExample(example);

        for (Long menuId: menuIds){
            RoleMenu roleMenu = new RoleMenu();

            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleInfo.getRoleId());
            this.roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.batchDelete(list, "roleId", RoleInfo.class);

        this.roleMenuService.deleteRoleMenusByRoleId(roleIds);
        this.userRoleService.deleteUserRoleByRoleId(roleIds);

    }
}
