package com.aries.orion.service;

import com.aries.orion.model.po.Article;
import com.aries.orion.model.po.Course;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StudyService {
    int addCourse(Course course);

    int updateCourse(MultipartFile file, Long id);

    void getImage(Long id, HttpServletResponse response);

    List<Course> getAllCourse();

    Course getCourseById(Long id);

    List<Article> getArticleListByCourseId(Long courseId);
}
