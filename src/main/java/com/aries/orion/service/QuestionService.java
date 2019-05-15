package com.aries.orion.service;

import com.aries.orion.model.po.AcQuestion;
import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.vo.QuestionVO;
import com.aries.orion.model.vo.UserRankVO;

import java.util.List;

public interface QuestionService {
    int upload(Question question);

    List<QuestionVO> getQuestionList(int page, int pageSize);

    List<QuestionVO> getQuestionByCategoryId(Integer categoryId);

    List<Category> selectAllCategory();

    Category selectCategoryByPrimary(Integer id);

    Question selectQuestionByPrimary(Long id);

    List<Category> getCategoryList();

    String doExercise(Question question);

    AcQuestion getAcByQuestionId(Long questionId);

    List<UserRankVO> getRanking();
}
