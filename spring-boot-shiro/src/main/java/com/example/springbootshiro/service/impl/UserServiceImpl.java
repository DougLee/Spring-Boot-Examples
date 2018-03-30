package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.UserMapper;
import com.example.springbootshiro.dao.UserRoleMapper;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.domain.UserRole;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.UserRoleService;
import com.example.springbootshiro.service.UserService;
import com.example.springbootshiro.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<UserInfo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserInfo findByName(String userName) {
        Example example = new Example(UserInfo.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());

        List<UserInfo> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void registUser(UserInfo userInfo) {

    }

    /**
     * 添加用户
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(UserInfo userInfo, Long[] roles) {
        userInfo.setCreateTime(new Date());
        userInfo.setPassword(MD5Utils.encrypt(userInfo.getUsername(), userInfo.getPassword()));
        this.save(userInfo);

        for (Long roleId: roles){
            UserRole ur = new UserRole();
            ur.setUserId(userInfo.getUserId());
            ur.setRoleId(roleId);

            this.userRoleMapper.insert(ur);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(UserInfo userInfo, Long[] roles) {
        userInfo.setPassword(null);
        userInfo.setUsername(null);
        userInfo.setModifyTime(new Date());
        this.updateNotNull(userInfo);

        Example example = new Example(UserRole.class);
        example.createCriteria().andCondition("user_id=", userInfo.getUserId());
        this.userRoleMapper.deleteByExample(example);
        for (Long roleId : roles) {
            UserRole ur = new UserRole();
            ur.setUserId(userInfo.getUserId());
            ur.setRoleId(roleId);
            this.userRoleMapper.insert(ur);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.batchDelete(list, "userId", UserInfo.class);

        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLoginTime(String userName) {
        Example example = new Example(UserInfo.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
        UserInfo user = new UserInfo();
        user.setLastLoginTime(new Date());
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePassword(String password) {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        Example example = new Example(UserInfo.class);
        example.createCriteria().andCondition("username=", user.getUsername());
        String newPassword = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        user.setPassword(newPassword);
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public UserInfo findUserProfile(UserInfo user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUserProfile(UserInfo user) {
        user.setUsername(null);
        user.setPassword(null);
        this.updateNotNull(user);
    }
}
