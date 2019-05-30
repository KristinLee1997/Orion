package com.aries.orion.service.impl;

import com.aries.orion.constants.SystemConstants;
import com.aries.orion.constants.SystemStatus;
import com.aries.orion.enums.QuestionDifficultyEnum;
import com.aries.orion.enums.QuestionTypeEnum;
import com.aries.orion.mapper.AcQuestionMapper;
import com.aries.orion.mapper.CategoryMapper;
import com.aries.orion.mapper.QuestionMapper;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.po.AcQuestion;
import com.aries.orion.model.po.AcQuestionExample;
import com.aries.orion.model.po.Category;
import com.aries.orion.model.po.CategoryExample;
import com.aries.orion.model.po.Question;
import com.aries.orion.model.po.QuestionExample;
import com.aries.orion.model.vo.QuestionVO;
import com.aries.orion.model.vo.UserRankVO;
import com.aries.orion.service.QuestionService;
import com.aries.orion.utils.DateUtils;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserInfo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private AcQuestionMapper acQuestionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int upload(Question question) {
        int i = questionMapper.insertSelective(question);
        return i;
    }

    @Override
    public List<QuestionVO> getQuestionList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andAuditEqualTo(1);
        example.setOrderByClause("id asc");
        List<Question> questionList = questionMapper.selectByExample(example);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (Question question : questionList) {
            questionVOList.add(convert2QuestionVO(question));
        }
        return questionVOList;
    }

    @Override
    public Integer getQuestionCount() {
        QuestionExample example = new QuestionExample();
        example.createCriteria();
        int count = questionMapper.countByExample(example);
        return count;
    }

    @Override
    public List<QuestionVO> getQuestionByCategoryId(Integer categoryId) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
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
    public List<Category> getCategoryList() {
        CategoryExample example = new CategoryExample();
        example.createCriteria();
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
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

            redisTemplate.opsForZSet().incrementScore(SystemConstants.REDIS_RANKING_NAME, acQuestion.getGaeaId(), 1);
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

    @Override
    public List<UserRankVO> getRanking() {
        List<UserRankVO> userRankVOList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SystemConstants.REDIS_RANKING_NAME, 0, -1);
        if (rangeByScoreWithScores == null || rangeByScoreWithScores.size() <= 0) {
            AcQuestionExample example = new AcQuestionExample();
            example.createCriteria();
            List<AcQuestion> acQuestionList = acQuestionMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(acQuestionList)) {
                return new ArrayList<>();
            }
            for (AcQuestion acQuestion : acQuestionList) {
                redisTemplate.opsForZSet().incrementScore(SystemConstants.REDIS_RANKING_NAME, acQuestion.getGaeaId(), 1);
            }
            rangeByScoreWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SystemConstants.REDIS_RANKING_NAME, 0, -1);
        }
        if (rangeByScoreWithScores == null || rangeByScoreWithScores.size() <= 0) {
            return new ArrayList<>();
        }
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = rangeByScoreWithScores.iterator();
        while (iterator.hasNext()) {
            UserRankVO userRankVO = new UserRankVO();
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            userRankVO.setGaeaId(((Integer) next.getValue()).longValue());
            userRankVO.setAcTotal(Objects.requireNonNull(next.getScore()).longValue());
            System.out.println("value:" + next.getValue() + " score:" + next.getScore());
            userRankVOList.add(userRankVO);
        }
        List<Long> userIdList = userRankVOList.stream().map(UserRankVO::getGaeaId).collect(Collectors.toList());
        Map<Long, UserInfo> userInfoMap = (Map) UserUtils.getUserInfoByIdList(userIdList).getData();
        for (UserRankVO userRankVO : userRankVOList) {
            userRankVO.setUserName(userInfoMap.get(userRankVO.getGaeaId()).getNickname());
            userRankVO.setRegisterDays(DateUtils.getDay(
                    DateUtils.convertString2Date(userInfoMap.get(userRankVO.getGaeaId()).getAddTime())
                    , new Date()));
        }
        return userRankVOList;
    }
}
