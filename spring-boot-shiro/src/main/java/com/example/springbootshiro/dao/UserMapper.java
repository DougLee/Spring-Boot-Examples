package com.example.springbootshiro.dao;

import com.example.springbootshiro.config.MyMapper;
import com.example.springbootshiro.domain.UserInfo;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface UserMapper extends MyMapper<UserInfo> {

    UserInfo findUserProfile(UserInfo user);
}
