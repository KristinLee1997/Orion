package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.orion.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        System.out.println("后端注册被调用了=====");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "{\"ok\":123}";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) {
        System.out.println("后端登录被调用了=====");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "{\"ok\":123}";
    }

    @GetMapping("/test")
    public String test() {
        return "login";
    }


}
