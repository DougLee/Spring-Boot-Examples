package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.UserMapper;
import com.example.springbootshiro.dao.UserRoleMapper;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Douglee on 2018/3/29.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserInfo findByName(String userName) {
        return null;
    }

    @Override
    public void registUser(UserInfo userInfo) {

    }

    @Override
    public void addUser(UserInfo userInfo, Long[] roles) {

    }

    @Override
    public void updateUser(UserInfo userInfo, Long[] roles) {

    }

    @Override
    public void deleteUsers(String userIds) {

    }

    @Override
    public void updateLoginTime(String userName) {

    }

    @Override
    public void updatePassword(String password) {

    }

    @Override
    public UserInfo findUserProfile(UserInfo user) {
        return null;
    }

    @Override
    public void updateUserProfile(UserInfo user) {

    }
}
