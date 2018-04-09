package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.MenuInfo;
import com.example.springbootshiro.domain.ResponseResult;
import com.example.springbootshiro.domain.Tree;
import com.example.springbootshiro.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Douglee on 2018/4/9.
 */
@Controller
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("menu")
    public String index(){
        return "menu/menu";
    }

    @RequestMapping("menu/menu")
    @ResponseBody
    public ResponseResult getMenu(String userName){
        try {
            List<MenuInfo> menus = menuService.findUserMenus(userName);
            return ResponseResult.ok();
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseResult.error("获取菜单失败");
        }
    }

    @RequestMapping("menu/tree")
    @ResponseBody
    public ResponseResult getMenuTree() {
        try {
            Tree<MenuInfo> tree = this.menuService.getMenuTree();
            return ResponseResult.ok(tree);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("获取菜单列表失败！");
        }
    }

    @RequestMapping("menu/menuButtonTree")
    @ResponseBody
    public ResponseResult getMenuButtonTree() {
        try {
            Tree<MenuInfo> tree = this.menuService.getMenuButtonTree();
            return ResponseResult.ok(tree);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("获取菜单列表失败！");
        }
    }

    @RequestMapping("menu/getMenu")
    @ResponseBody
    public ResponseResult getMenu(Long menuId){
        try {
            MenuInfo menuInfo = menuService.findById(menuId);
            return ResponseResult.ok(menuInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseResult.error("获取信息失败");
        }
    }


    @RequestMapping("menu/getUserMenu")
    @ResponseBody
    public ResponseResult getUserMenu(String userName){
        try {
            Tree<MenuInfo> tree = menuService.getUserMenu(userName);
            return ResponseResult.ok(tree);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseResult.error("获取用户菜单失败");
        }
    }

    @RequestMapping("menu/list")
    @ResponseBody
    public List<MenuInfo> menuList(MenuInfo menuInfo){
        try {
            return menuService.findAllMenus(menuInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions("menu:add")
    @RequestMapping("menu/add")
    @ResponseBody
    public ResponseResult addMenu(MenuInfo menuInfo){
        String name = "";
        if (MenuInfo.TYPE_MENU.equals(menuInfo.getType())){
            name = "菜单";
        }else {
            name = "按钮";
        }

        try {
            menuService.addMenu(menuInfo);
            return ResponseResult.ok("新增" + name + "成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseResult.ok("新增" + name + "失败");
        }
    }

    @RequiresPermissions("menu:delete")
    @RequestMapping("menu/delete")
    @ResponseBody
    public ResponseResult deleteMenus(String ids) {
        try {
            this.menuService.deleteMeuns(ids);
            return ResponseResult.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("删除失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("menu:update")
    @RequestMapping("menu/update")
    @ResponseBody
    public ResponseResult updateMenu(MenuInfo menu) {
        String name = "";
        if (MenuInfo.TYPE_MENU.equals(menu.getType()))
            name = "菜单";
        else
            name = "按钮";
        try {
            this.menuService.updateMenu(menu);
            return ResponseResult.ok("修改" + name + "成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("修改" + name + "失败，请联系网站管理员！");
        }
    }


}
