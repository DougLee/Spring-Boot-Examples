package com.example.springboothelloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Douglee on 2018/3/28.
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "Hello World";
    }
}
