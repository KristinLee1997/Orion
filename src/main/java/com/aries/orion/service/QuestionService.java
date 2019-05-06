package com.aries.orion.service;

import com.aries.orion.model.vo.UserRankVO;
import com.aries.orion.model.po.AcQuestion;
import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.vo.QuestionVO;

import java.util.List;

public interface QuestionService {
    int upload(Question question);

    List<QuestionVO> getQuestionList(int page, int pageSize);

    List<Category> selectAllCategory();

    Category selectCategoryByPrimary(Integer id);

    Question selectQuestionByPrimary(Long id);

    String doExercise(Question question);

    AcQuestion getAcByQuestionId(Long questionId);

    List<UserRankVO> getRanking();
}
