package com.aries.orion.service;

import com.aries.orion.model.po.Article;
import com.aries.orion.model.po.Course;

import java.util.List;

public interface StudyService {
    int addCourse(Course course);

    List<Course> getAllCourse();

    Course getCourseById(Long id);

    List<Article> getArticleListByCourseId(Long courseId);

    Article getArticleById(Long id);
}
