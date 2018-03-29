package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.MenuInfo;
import com.example.springbootshiro.domain.Tree;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface MenuService extends IService<MenuInfo> {

    List<MenuInfo> findUserPermissions(String userName);

    List<MenuInfo> findUserMenus(String userName);

    List<MenuInfo> findAllMenus(MenuInfo menu);

    Tree<MenuInfo> getMenuButtonTree();

    Tree<MenuInfo> getMenuTree();

    Tree<MenuInfo> getUserMenu(String userName);

    MenuInfo findById(Long menuId);

    MenuInfo findByNameAndType(String menuName, String type);

    void addMenu(MenuInfo menu);

    void updateMenu(MenuInfo menu);

    void deleteMeuns(String menuIds);
}
