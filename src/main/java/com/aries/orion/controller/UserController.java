package com.aries.orion.controller;

import com.aries.orion.model.User;
import com.aries.user.gaea.client.model.GaeaResponse;
import com.aries.user.gaea.client.model.UserRegisterVo;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        UserRegisterVo userRegisterVo = UserRegisterVo.PhoneNumberBuilder.anUserRegisterVo()
                .phoneNumber(user.getPhonenumber()).password(user.getPassword()).build();
        GaeaResponse response = null;
        response = UserUtils.register(userRegisterVo);
        if (response.getData() != null) {
            return (String) response.getData();
        } else {
            return "注册失败，请重新注册";
        }
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setLoginId(user.getPhonenumber());
        userLoginDTO.setPassword(user.getPassword());
        userLoginDTO.setLoginType(0);
        GaeaResponse response = UserUtils.login(userLoginDTO);
        if (response.getData() != null) {
            return (String) response.getData();
        } else {
            return "登录失败，请重新注册";
        }
    }

    @GetMapping("/loginto")
    public String test() {
        return "login";
    }

    @GetMapping("/reg")
    public String tesreg() {
        return "register";
    }

}
