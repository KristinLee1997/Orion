package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.orion.model.po.Question;
import com.aries.orion.service.QuestionService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Slf4j
@RequestMapping("/questions")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/list")
    public String list() {

        return "questions";
    }

    @GetMapping("/ranking")
    public String ranking() {
        return "ranking";
    }

    @PostMapping("/upload")
    public String upload(@RequestBody Question question) {
        System.out.println(JSON.toJSONString(question));
        int id = questionService.upload(question);
        log.info("上传题目成功，题号：{}", id);
        return "upload";
    }

    @GetMapping("/upload/test")
    public String uploadT() {
        return "upload";
    }

    @GetMapping("/aa")
    @ResponseBody
    public void selectAll() {
        PageHelper.startPage(2, 2);
//        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
//        System.out.println(JSON.toJSONString(questionList));
    }
}
