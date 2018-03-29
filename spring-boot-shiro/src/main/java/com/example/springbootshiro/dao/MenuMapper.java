package com.example.springbootshiro.dao;

import com.example.springbootshiro.domain.MenuInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface MenuMapper extends CommonMapper<MenuMapper>  {

    List<MenuInfo> findUserPermissions(String userName);

    List<MenuInfo> findUserMenus(String userName);
}
