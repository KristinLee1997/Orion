package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.SystemStatus;
import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.vo.QuestionVO;
import com.aries.orion.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/questions")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/list")
    public ModelAndView list() {
        List<QuestionVO> questionList = questionService.getQuestionList(1, 20);
        ModelAndView modelAndView = new ModelAndView("questions");
        modelAndView.addObject("questionvolist", questionList);
        return modelAndView;
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

    @GetMapping("/upload/detail")
    public ModelAndView uploadT() {
        List<Category> categoryList = questionService.selectAllCategory();
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("categorylist", categoryList);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView questiondetail(@PathVariable("id") Long id) {
        Question question = questionService.selectQuestionByPrimary(id);
        ModelAndView modelAndView = new ModelAndView("question-detail");
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @PostMapping("/exercise")
    @ResponseBody
    public String doExercise(@RequestBody Question question) {
        if (question.getId() == null || question.getType() == null) {
            return HttpResponse.of(SystemStatus.PARAM_NULL);
        }
        String response = questionService.doExercise(question);
        return response;
    }
}
