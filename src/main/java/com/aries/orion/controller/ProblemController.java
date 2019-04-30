package com.aries.orion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class ProblemController {

    @GetMapping("/list")
    public String list() {
        return "question";
    }

    @GetMapping("/ranking")
    public String ranking() {
        return "ranking";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
}
