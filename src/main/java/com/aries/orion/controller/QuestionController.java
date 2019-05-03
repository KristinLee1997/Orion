package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.orion.mapper.QuestionMapper;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.po.QuestionExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Resource
    private QuestionMapper questionMapper;

    @GetMapping("/list")
    public String list() {

        return "questions";
    }

    @GetMapping("/ranking")
    public String ranking() {
        return "ranking";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/aa")
    @ResponseBody
    public void selectAll() {
        PageHelper.startPage(2, 2);
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        System.out.println(JSON.toJSONString(questionList));
    }
}
