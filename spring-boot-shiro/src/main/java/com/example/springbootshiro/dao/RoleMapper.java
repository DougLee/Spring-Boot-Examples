package com.example.springbootshiro.dao;

import com.example.springbootshiro.config.MyMapper;
import com.example.springbootshiro.domain.RoleInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface RoleMapper extends MyMapper<RoleInfo> {

    List<RoleInfo> findUserRole(String userName);

}
