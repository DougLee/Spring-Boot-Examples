package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.ResponseResult;
import com.example.springbootshiro.domain.UserInfo;
import com.example.springbootshiro.service.UserService;
import com.example.springbootshiro.util.MD5Utils;
import com.example.springbootshiro.util.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Douglee on 2018/3/30.
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(String username, String password, String code, Boolean rememberMe) {
        if (StringUtils.isNullorEmpty(code)) {
            return ResponseResult.warn("验证码不能为空!");
        }
//        Session session = super.getSession();
//        String sessionCode = (String) session.getAttribute("_code");
//        if (!code.toLowerCase().equals(sessionCode)) {
//            return ResponseResult.warn("验证码错误！");
//        }

        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

        try {
            super.login(token);
            this.userService.updateLoginTime(username);
            return ResponseResult.ok();
        } catch (AuthenticationException e) {
            return ResponseResult.error("认证失败！");
        } catch (Exception e) {
            return ResponseResult.error(e.getMessage());
        }
    }
    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        UserInfo user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "index";
    }

}
