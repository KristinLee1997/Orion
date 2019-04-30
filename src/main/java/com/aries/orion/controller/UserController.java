package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.orion.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        System.out.println("后端被调用了=====");
        return ;
    }

    @GetMapping("/test")
    public String test() {
        return "login";
    }
}
