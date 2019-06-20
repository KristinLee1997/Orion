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

import javax.annotation.Resource;
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
    public List<Course> getAllCourse() {
        CourseExample example = new CourseExample();
        example.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(example);
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
        articleExample.setOrderByClause("number");
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        return articleList;
    }

    @Override
    public Article getArticleById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }
}
