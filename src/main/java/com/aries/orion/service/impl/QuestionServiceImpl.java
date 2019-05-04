package com.aries.orion.service.impl;

import com.aries.orion.mapper.QuestionMapper;
import com.aries.orion.model.po.Question;
import com.aries.orion.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Override
    public int upload(Question question) {
        int i = questionMapper.insertSelective(question);
        return i;
    }
}
