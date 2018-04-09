package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.QueryRequest;
import com.example.springbootshiro.domain.ResponseResult;
import com.example.springbootshiro.domain.RoleInfo;
import com.example.springbootshiro.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Douglee on 2018/4/9.
 */
@Controller
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @RequestMapping("role")
    public String index(){
        return "role/role";
    }

    @RequestMapping("role/list")
    @ResponseBody
    public Map<String, Object> roleList(QueryRequest request, RoleInfo roleInfo){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<RoleInfo> list = roleService.findAllRole(roleInfo);

        PageInfo<RoleInfo> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }


    @RequiresPermissions("role:add")
    @RequestMapping("role/add")
    @ResponseBody
    public ResponseResult addRole(RoleInfo role, Long[] menuId) {
        try {
            this.roleService.addRole(role, menuId);
            return ResponseResult.ok("新增角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("新增角色失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("role:delete")
    @RequestMapping("role/delete")
    @ResponseBody
    public ResponseResult deleteRoles(String ids) {
        try {
            this.roleService.deleteRoles(ids);
            return ResponseResult.ok("删除角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("删除角色失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("role:update")
    @RequestMapping("role/update")
    @ResponseBody
    public ResponseResult updateRole(RoleInfo role, Long[] menuId) {
        try {
            this.roleService.updateRole(role, menuId);
            return ResponseResult.ok("修改角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("修改角色失败，请联系网站管理员！");
        }
    }


}
