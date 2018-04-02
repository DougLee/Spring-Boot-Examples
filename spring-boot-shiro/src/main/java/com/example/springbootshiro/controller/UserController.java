package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.QueryRequest;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("user")
    public String index(Model model) {
        UserInfo userInfo = super.getCurrentUser();
        model.addAttribute("user", userInfo);
        return "user/user";
    }

    @GetMapping("user/list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, UserInfo userInfo) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<UserInfo> list = userService.getUserList();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);

        return getDataTable(pageInfo);
    }

}
