package com.aries.orion.service.impl;

import com.aries.hermes.client.thrift.exception.CallFailedException;
import com.aries.hermes.client.thrift.exception.PageSizeLimitException;
import com.aries.hermes.client.thrift.facade.ReplyFacade;
import com.aries.hermes.client.thrift.facade.TopicFacade;
import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.hermes.client.thrift.vo.TopicVO;
import com.aries.orion.service.DiscussService;
import com.aries.orion.model.vo.DisscussVo;
import com.aries.user.gaea.client.model.GaeaResponse;
import com.aries.user.gaea.client.model.User;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscussServiceImpl implements DiscussService {
    @Override
    public List<DisscussVo> getDiscussList(Integer page, Integer pageSize) {
        List<TopicVO> topicVOList = null;
        try {
            topicVOList = TopicFacade.batchQueryTopics(page, pageSize);
        } catch (PageSizeLimitException e) {
            log.error("获取主帖列表失败，分页查询page，pageSize过大，参数：page:{},pageSize:{}", page, pageSize);
            return Collections.emptyList();
        } catch (CallFailedException e) {
            log.error("获取主帖服务找不到，参数：page:{},pageSize:{}", page, pageSize);
            return Collections.emptyList();
        }
        List<DisscussVo> disscussVoList = new ArrayList<>();
        List<Long> userIdList = topicVOList.stream().map(TopicVO::getGaeaId).collect(Collectors.toList());
        Map<Long, UserInfo> userInfo = (Map) UserUtils.getUserInfoByIdList(userIdList).getData();

        for (TopicVO topicVO : topicVOList) {
            DisscussVo disscussVo = new DisscussVo();
            disscussVo.setId(topicVO.getId());
            disscussVo.setTheme(topicVO.getTheme());
            disscussVo.setInsertTime(topicVO.getInsertTime());
            disscussVo.setGaeaid(topicVO.getGaeaId());
            disscussVo.setUsername(userInfo.get(topicVO.getGaeaId()).getNickname());
            disscussVo.setReplyNum(280);
            disscussVoList.add(disscussVo);
        }
        return disscussVoList;
    }

    @Override
    public DisscussVo getDiscussById(Long topicId) {
        TopicVO topicVO = null;
        try {
            topicVO = TopicFacade.queryById(topicId);
        } catch (CallFailedException e) {
            log.error("通过id查询主帖失败,参数topicId:{}", topicId);
            return null;
        }
        DisscussVo disscussVo = convertTopicVO2DiscussVo(topicVO);
        return disscussVo;
    }

    @Override
    public List<ReplyVO> getDiscussDetail(Long topicId, Integer page, Integer pageSize) {
        List<ReplyVO> replyVOS = null;
        try {
            replyVOS = ReplyFacade.batchQueryByTopicId(topicId, 1, 20);
        } catch (PageSizeLimitException e) {
            log.warn("获取子帖失败，参数topicId:{},page:{},pageSize:{}", topicId, page, pageSize);
            return Collections.emptyList();
        } catch (CallFailedException e) {
            log.warn("获取子帖服务找不到，参数topicId:{},page:{},pageSize:{}", topicId, page, pageSize);
            return Collections.emptyList();
        }
        return replyVOS;
    }

    private DisscussVo convertTopicVO2DiscussVo(TopicVO topicVO) {
        GaeaResponse gaeaResponse = UserUtils.getUserInfoById(topicVO.getGaeaId());
        User user = (User) gaeaResponse.getData();
        DisscussVo disscussVo = new DisscussVo();
        disscussVo.setId(topicVO.getId());
        disscussVo.setTheme(topicVO.getTheme());
        disscussVo.setContent(topicVO.getContent());
        disscussVo.setInsertTime(topicVO.getInsertTime());
        disscussVo.setGaeaid(topicVO.getGaeaId());
        disscussVo.setUsername(user.getNickname());
        disscussVo.setCategoryId(topicVO.getCategoryId());
        disscussVo.setReplyNum(280);
        return disscussVo;
    }

}
