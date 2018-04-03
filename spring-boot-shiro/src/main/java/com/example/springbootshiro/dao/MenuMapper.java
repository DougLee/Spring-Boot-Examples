package com.example.springbootshiro.dao;

import com.example.springbootshiro.config.MyMapper;
import com.example.springbootshiro.domain.MenuInfo;

import java.util.List;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface MenuMapper extends MyMapper<MenuInfo> {

    List<MenuInfo> findUserPermissions(String userName);

    List<MenuInfo> findUserMenus(String userName);

    /**
     * 删除父节点, 子节点变成顶级节点
     * @param menuIds
     */
    void changToTop(List<String> menuIds);
}
