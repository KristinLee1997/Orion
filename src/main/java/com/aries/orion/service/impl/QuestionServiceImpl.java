package com.aries.orion.service.impl;

import com.aries.orion.enums.QuestionDifficultyEnum;
import com.aries.orion.enums.QuestionTypeEnum;
import com.aries.orion.mapper.AcQuestionMapper;
import com.aries.orion.mapper.CategoryMapper;
import com.aries.orion.mapper.QuestionMapper;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.SystemStatus;
import com.aries.orion.model.po.AcQuestion;
import com.aries.orion.model.po.AcQuestionExample;
import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.po.QuestionExample;
import com.aries.orion.model.vo.QuestionVO;
import com.aries.orion.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private AcQuestionMapper acQuestionMapper;

    @Override
    public int upload(Question question) {
        int i = questionMapper.insertSelective(question);
        return i;
    }

    @Override
    public List<QuestionVO> getQuestionList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("id asc");
        List<Question> questionList = questionMapper.selectByExample(example);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (Question question : questionList) {
            questionVOList.add(convert2QuestionVO(question));
        }
        return questionVOList;
    }

    private QuestionVO convert2QuestionVO(Question question) {
        QuestionVO questionVo = new QuestionVO();
        questionVo.setId(question.getId());
        questionVo.setType(QuestionTypeEnum.getTypeName(question.getType()));
        questionVo.setTitle(question.getTitle());
        questionVo.setDescription(question.getDescription());
        questionVo.setAnswer(question.getAnswer());
        questionVo.setCategoryId(question.getCategoryId());
        questionVo.setCategoryName(selectCategoryByPrimary(question.getCategoryId()).getCategoryName());
        questionVo.setDifficulty(QuestionDifficultyEnum.getLevel(question.getDifficulty()));
        if (question.getType().equals(QuestionTypeEnum.SelectionType.getId())) {
            questionVo.setSelection(question.getSelection());
        }
        questionVo.setPassNum(22);
        return questionVo;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = categoryMapper.selectByExample(null);
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }
        return categoryList;
    }

    @Override
    public Category selectCategoryByPrimary(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public Question selectQuestionByPrimary(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        return question;
    }

    @Override
    public String doExercise(Question question) {
        Question que = selectQuestionByPrimary(question.getId());
        if (!question.getAnswer().equals(que.getAnswer())) {
            return HttpResponse.of(SystemStatus.WRONG_ANSWER);
        }
        AcQuestion ac = getAcByQuestionId(question.getId());
        if (ac == null) {
            AcQuestion acQuestion = new AcQuestion() {{
                setGaeaId(6L);
                setQuestionId(question.getId());
            }};
            acQuestionMapper.insertSelective(acQuestion);
        }
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @Override
    public AcQuestion getAcByQuestionId(Long questionId) {
        AcQuestionExample example = new AcQuestionExample();
        example.createCriteria().andQuestionIdEqualTo(questionId);
        List<AcQuestion> acQuestionList = acQuestionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(acQuestionList) || acQuestionList.size() == 0) {
            return null;
        }
        return acQuestionList.get(0);
    }
}
