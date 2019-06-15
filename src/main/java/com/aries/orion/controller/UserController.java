package com.aries.orion.controller;

import com.aries.orion.constants.SystemStatus;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.User;
import com.aries.user.gaea.client.model.GaeaResponse;
import com.aries.user.gaea.client.model.UserRegisterVo;
import com.aries.user.gaea.client.model.UserVo;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    public String login(@RequestBody User user, HttpServletRequest httpServletRequest) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setLoginId(user.getPhonenumber());
        userLoginDTO.setPassword(user.getPassword());
        userLoginDTO.setLoginType(0);
        GaeaResponse response = UserUtils.login(userLoginDTO);
        if (response.getData() != null) {
            UserVo userVo = (UserVo) response.getData();
            System.out.println(userVo.getCookie());
            httpServletRequest.getSession().setAttribute("ticket", userVo.getCookie());
            return HttpResponse.of(SystemStatus.SUCCESS);
        } else {
            System.out.println("登录失败，请重新登录");
            return HttpResponse.of(SystemStatus.SYSTEM_ERROR);
        }
    }

    @GetMapping("/loginto")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/reg")
    public String tesreg() {
        return "register";
    }

}
