package com.aries.orion.service;

import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.vo.QuestionVO;

import java.util.List;

public interface QuestionService {
    int upload(Question question);

    List<QuestionVO> getQuestionList(int page, int pageSize);

    List<Category> selectAllCategory();

    Category selectByPrimary(Integer id);
}
