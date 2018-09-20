package com.example.springboothelloworld.controller;

import com.example.springboothelloworld.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Douglee on 2018/3/28.
 */
@RestController
public class IndexController {

    @Autowired
    private AuthorSettings authorSettings;

    @GetMapping("/")
    public String index(){
        return "Hello World " + authorSettings.getName();
    }
}
