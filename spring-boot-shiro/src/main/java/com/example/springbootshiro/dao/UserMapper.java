package com.example.springbootshiro.dao;

import com.example.springbootshiro.domain.UserInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface UserMapper extends CommonMapper<UserInfo> {

    UserInfo findUserProfile(UserInfo user);
}
