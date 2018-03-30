package com.example.springbootshiro.controller;

import com.example.springbootshiro.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Douglee on 2018/3/30.
 */
public class BaseController {

    protected Map<String, Object> getDataTable(PageInfo<?> pageInfo){
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getList());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected UserInfo getCurrentUser() {
        return (UserInfo) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }
}
