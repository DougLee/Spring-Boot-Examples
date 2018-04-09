package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.QueryRequest;
import com.example.springbootshiro.domain.ResponseResult;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Douglee on 2018/4/2.
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("user")
    public String index(Model model) {
        UserInfo userInfo = super.getCurrentUser();
        model.addAttribute("user", userInfo);
        return "user/user";
    }

    @RequestMapping("user/list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, UserInfo userInfo) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<UserInfo> list = userService.getUserList();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);

        return getDataTable(pageInfo);
    }

    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public ResponseResult addUser(UserInfo user, Long[] roles) {
        try {
            if ("on".equalsIgnoreCase(user.getStatus()))
                user.setStatus("1");
            else
                user.setStatus("0");
            this.userService.addUser(user, roles);
            return ResponseResult.ok("新增用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("新增用户失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("user:update")
    @RequestMapping("user/update")
    @ResponseBody
    public ResponseResult updateUser(UserInfo user, Long[] rolesSelect) {
        try {
            if ("on".equalsIgnoreCase(user.getStatus()))
                user.setStatus("1");
            else
                user.setStatus("0");
            this.userService.updateUser(user, rolesSelect);
            return ResponseResult.ok("修改用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("修改用户失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    @ResponseBody
    public ResponseResult deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseResult.ok("删除用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("删除用户失败，请联系网站管理员！");
        }
    }

}
