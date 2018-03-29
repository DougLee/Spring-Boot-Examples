package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.UserInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface UserService extends IService<UserInfo> {

    UserInfo findByName(String userName);

    void registUser(UserInfo userInfo);

    void addUser(UserInfo userInfo, Long[] roles);

    void updateUser(UserInfo userInfo, Long[] roles);

    void deleteUsers(String userIds);

    void updateLoginTime(String userName);
    void updatePassword(String password);
    UserInfo findUserProfile(UserInfo user);

    void updateUserProfile(UserInfo user);
}
