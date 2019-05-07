package com.aries.orion.controller;

import com.aries.orion.constants.SystemStatus;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.po.Article;
import com.aries.orion.model.po.Course;
import com.aries.orion.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/study")
@Slf4j
public class StudyController {
    @Resource
    private StudyService studyService;

    @PostMapping("/add/course")
    public String addCourse(@RequestBody Course course) {
        int i = studyService.addCourse(course);
        if (i <= 0) {
            return HttpResponse.of(SystemStatus.DATABASE_ERROR);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @PostMapping("/update/course")
    @ResponseBody
    public String updateCourse(MultipartFile file, Long id) {
        int i = studyService.updateCourse(file, id);
        if (i <= 0) {
            return HttpResponse.of(SystemStatus.DATABASE_ERROR);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @GetMapping("/getImage/{id}")
    public void getImage(@PathVariable("id") Long id, HttpServletResponse response) {
        if (id == null || id <= 0) {
            log.warn("获取课程图片参数为null");
            return;
        }
        studyService.getImage(id, response);
    }

    @GetMapping("/list")
    public ModelAndView study() {
        List<Course> courseList = studyService.getAllCourse();
        ModelAndView modelAndView = new ModelAndView("study");
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }

    @GetMapping("/course/{id}")
    public ModelAndView getCourseById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("course_detail");
        Course course = studyService.getCourseById(id);
        List<Article> articleList = studyService.getArticleListByCourseId(id);
        modelAndView.addObject("course", course);
        modelAndView.addObject("articleList", articleList);
        return modelAndView;
    }
}
