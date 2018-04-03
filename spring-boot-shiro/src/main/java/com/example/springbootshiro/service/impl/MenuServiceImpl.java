package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.MenuMapper;
import com.example.springbootshiro.domain.MenuInfo;
import com.example.springbootshiro.domain.Tree;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.MenuService;
import com.example.springbootshiro.service.RoleMenuService;
import com.example.springbootshiro.util.StringUtils;
import com.example.springbootshiro.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseService<MenuInfo> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<MenuInfo> findUserPermissions(String userName) {
        return this.menuMapper.findUserPermissions(userName);
    }

    @Override
    public List<MenuInfo> findUserMenus(String userName) {
        return menuMapper.findUserMenus(userName);
    }

    @Override
    public List<MenuInfo> findAllMenus(MenuInfo menu) {
        Example example = new Example(MenuInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isNullorEmpty(menu.getMenuName())) {
            criteria.andCondition("menu_name=", menu.getMenuName());
        }
        if (!StringUtils.isNullorEmpty(menu.getType())){
            criteria.andCondition("type=", Long.valueOf(menu.getType()));
        }

        example.setOrderByClause("menu_id");

        return this.selectByExample(example);
    }

    @Override
    public Tree<MenuInfo> getMenuButtonTree() {
        List<Tree<MenuInfo>> trees = new ArrayList<>();
        List<MenuInfo> menus = this.findAllMenus(new MenuInfo());
        for (MenuInfo menuInfo: menus){
            Tree<MenuInfo> tree = new Tree<>();
            tree.setId(menuInfo.getMenuId().toString());
            tree.setParentId((menuInfo.getParentId().toString()));
            tree.setText(menuInfo.getMenuName());
            trees.add(tree);
        }
        Tree<MenuInfo> t = TreeUtils.build(trees);
        return t;
    }

    @Override
    public Tree<MenuInfo> getMenuTree() {
        List<Tree<MenuInfo>> trees = new ArrayList<>();
        Example example = new Example(MenuInfo.class);
        example.createCriteria().andCondition("type=", 0);
        example.setOrderByClause("create_time");
        List<MenuInfo> menus = this.menuMapper.selectByExample(example);
        for (MenuInfo menuInfo: menus){
            Tree<MenuInfo> tree = new Tree<>();
            tree.setId(menuInfo.getMenuId().toString());
            tree.setParentId(menuInfo.getParentId().toString());
            tree.setText(menuInfo.getMenuName());

            trees.add(tree);
        }

        Tree<MenuInfo> t = TreeUtils.build(trees);
        return t;
    }

    @Override
    public Tree<MenuInfo> getUserMenu(String userName) {

        List<Tree<MenuInfo>> trees = new ArrayList<>();
        List<MenuInfo> menus = findUserMenus(userName);

        for (MenuInfo menu : menus) {
            Tree<MenuInfo> tree = new Tree<MenuInfo>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getMenuName());
            tree.setUrl(menu.getUrl());
            trees.add(tree);
        }
        Tree<MenuInfo> t = TreeUtils.build(trees);
        return t;
    }

    @Override
    public MenuInfo findById(Long menuId) {
        return selectByKey(menuId);
    }

    @Override
    public MenuInfo findByNameAndType(String menuName, String type) {
        Example example = new Example(MenuInfo.class);
        example.createCriteria().andCondition("lower(menu_name)=", menuName.toLowerCase())
                .andEqualTo("type", Long.valueOf(type));
        List<MenuInfo> list = menuMapper.selectByExample(example);
        if (list.size() == 0) {
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMenu(MenuInfo menu) {
        menu.setCreateTime(new Date());
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        this.save(menu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMenu(MenuInfo menu) {
        menu.setModifyTime(new Date());
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        this.updateNotNull(menu);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteMeuns(String menuIds) {
        List<String> list = Arrays.asList(menuIds.split(","));
        batchDelete(list, "menuId", MenuInfo.class);
        roleMenuService.deleteRoleMenusByMenuId(menuIds);
        menuMapper.changToTop(list);
    }
}
