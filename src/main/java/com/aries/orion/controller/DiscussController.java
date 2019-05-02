package com.aries.orion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscussController {
    @GetMapping("/discuss")
    public String discuss() {

        return "discuss";
    }
}
