package com.aries.orion.service.impl;

import com.aries.orion.mapper.ArticleMapper;
import com.aries.orion.mapper.CourseMapper;
import com.aries.orion.model.po.Article;
import com.aries.orion.model.po.ArticleExample;
import com.aries.orion.model.po.Course;
import com.aries.orion.model.po.CourseExample;
import com.aries.orion.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class StudyServiceImpl implements StudyService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int addCourse(Course course) {
        int i = courseMapper.insertSelective(course);
        return i;
    }

    @Override
    public int updateCourse(MultipartFile file, Long id) {
        Course course = new Course();
        course.setId(id);
        try {
            course.setImage(file.getBytes());
            course.setImageName(file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = courseMapper.updateByPrimaryKeySelective(course);
        return i;
    }

    @Override
    public void getImage(Long id, HttpServletResponse response) {
        Course course = courseMapper.selectByPrimaryKey(id);
        if (course.getImageName() == null || course.getImage() == null) {
            return;
        }
        response.setContentType("image/" + getTypeByImageName(course.getImageName()));
        response.setCharacterEncoding("UTF-8");
        try {
            OutputStream outputStream = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(course.getImage());
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
        } catch (Exception e) {
            log.error("读取课程图片异常");
        }
    }

    private String getTypeByImageName(String name) {
        String[] strName = name.split("\\.");
        System.out.println(name);
        String type = strName[strName.length - 1];
        return type;
    }

    @Override
    public List<Course> getAllCourse() {
        CourseExample example = new CourseExample();
        example.createCriteria();
        List<Course> courseList = courseMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isEmpty(courseList)) {
            return Collections.emptyList();
        }
        return courseList;
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    @Override
    public List<Article> getArticleListByCourseId(Long courseId) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andCourseIdEqualTo(courseId);
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        return articleList;
    }
}
