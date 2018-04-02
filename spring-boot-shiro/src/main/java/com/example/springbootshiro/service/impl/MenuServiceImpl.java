package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.MenuMapper;
import com.example.springbootshiro.domain.MenuInfo;
import com.example.springbootshiro.domain.Tree;
import com.example.springbootshiro.service.BaseService;
import com.example.springbootshiro.service.MenuService;
import com.example.springbootshiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglee on 2018/4/2.
 */
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseService<MenuInfo> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

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
        Tree<MenuInfo> t = null;
        return t;
    }

    @Override
    public Tree<MenuInfo> getMenuTree() {
        return null;
    }

    @Override
    public Tree<MenuInfo> getUserMenu(String userName) {
        return null;
    }

    @Override
    public MenuInfo findById(Long menuId) {
        return null;
    }

    @Override
    public MenuInfo findByNameAndType(String menuName, String type) {
        return null;
    }

    @Override
    public void addMenu(MenuInfo menu) {

    }

    @Override
    public void updateMenu(MenuInfo menu) {

    }

    @Override
    public void deleteMeuns(String menuIds) {

    }
}
