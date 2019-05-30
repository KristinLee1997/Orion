package com.aries.orion.service.impl;

import com.aries.hera.client.thrift.exception.ServiceNotFoundException;
import com.aries.hermes.client.thrift.exception.CallFailedException;
import com.aries.hermes.client.thrift.exception.PageSizeLimitException;
import com.aries.hermes.client.thrift.facade.ReplyFacade;
import com.aries.hermes.client.thrift.facade.TopicFacade;
import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.hermes.client.thrift.vo.TopicVO;
import com.aries.hermes.idl.dto.ThriftResponse;
import com.aries.orion.model.vo.DisscussVo;
import com.aries.orion.service.DiscussService;
import com.aries.user.gaea.client.model.GaeaResponse;
import com.aries.user.gaea.client.model.User;
import com.aries.user.gaea.client.utils.UserUtils;
import com.aries.user.gaea.contact.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscussServiceImpl implements DiscussService {
    @Override
    public List<DisscussVo> getDiscussList(Integer page, Integer pageSize) {
        List<TopicVO> topicVOList = null;
        try {
            topicVOList = TopicFacade.batchQueryTopics(0, page, pageSize);
        } catch (PageSizeLimitException e) {
            log.error("获取主帖列表失败，分页查询page，pageSize过大，参数：page:{},pageSize:{}", page, pageSize);
            return Collections.emptyList();
        } catch (CallFailedException e) {
            log.error("获取主帖服务找不到，参数：page:{},pageSize:{}", page, pageSize);
            return Collections.emptyList();
        }
        List<DisscussVo> disscussVoList = new ArrayList<>();
        Set<Long> userIdset = topicVOList.stream().map(TopicVO::getGaeaId).collect(Collectors.toSet());
        List<Long> userIdList = new ArrayList<>(userIdset);
        Map<Long, UserInfo> userInfo = new HashMap<>();
        try {
            userInfo = (Map) UserUtils.getUserInfoByIdList(userIdList).getData();
        } catch (Exception e) {
            System.out.println("Message: " + e);
        }


        for (TopicVO topicVO : topicVOList) {
            DisscussVo disscussVo = new DisscussVo();
            disscussVo.setId(topicVO.getId());
            disscussVo.setTheme(topicVO.getTheme());
            disscussVo.setInsertTime(topicVO.getInsertTime());
            disscussVo.setGaeaid(topicVO.getGaeaId());
            disscussVo.setImageId(userInfo.get(topicVO.getGaeaId()).getImageId());
            disscussVo.setUsername(userInfo.get(topicVO.getGaeaId()).getNickname());
            Long replyCount = 0L;
            try {
                replyCount = ReplyFacade.getReplyCount(topicVO.getId());
            } catch (TTransportException e) {
                e.printStackTrace();
            }
            disscussVo.setReplyNum(replyCount);
            disscussVoList.add(disscussVo);
        }
        return disscussVoList;
    }

    @Override
    public Long getDiscussCount() {
        Long count = 0L;
        try {
            count = TopicFacade.getTopicCount(0);
        } catch (TTransportException e) {
            log.error("获取主帖总数失败");
            return 0L;
        }
        return count;
    }

    @Override
    public Long getReplyCount(Long topicId) {
        Long replyCount = 0L;
        try {
            replyCount = ReplyFacade.getReplyCount(topicId);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    public Boolean addTopic(DisscussVo disscussVo) {
        disscussVo.setGaeaid(6L);
        ThriftResponse response = null;
        try {
            response = TopicFacade.addTopic(convertDisscussVo2TopicVO(disscussVo));
        } catch (ServiceNotFoundException e) {
            log.error("发送主帖服务找不到");
            return false;
        } catch (TTransportException e) {
            log.error("发送主帖服务异常");
            return false;
        }
        return true;
    }

    @Override
    public Boolean addReply(ReplyVO replyVO) {
        try {
            ThriftResponse response = ReplyFacade.addReply(replyVO);
        } catch (ServiceNotFoundException e) {
            log.error("回复主帖服务找不到");
            return false;
        } catch (TTransportException e) {
            log.error("回复主帖服务异常");
            return false;
        }
        return true;
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
        Long replyCount = 0L;
        try {
            replyCount = ReplyFacade.getReplyCount(topicVO.getId());
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        disscussVo.setReplyNum(replyCount);
        return disscussVo;
    }

    private TopicVO convertDisscussVo2TopicVO(DisscussVo disscussVo) {
        TopicVO.TopicVOBuilder topicVOBuilder = TopicVO.TopicVOBuilder.aTopicVO();
        if (disscussVo.getTheme() != null) {
            topicVOBuilder.theme(disscussVo.getTheme());
        }
        if (disscussVo.getContent() != null) {
            topicVOBuilder.content(disscussVo.getContent());
        }
        if (disscussVo.getCategoryId() != null) {
            topicVOBuilder.categoryId(disscussVo.getCategoryId());
        }
        if (disscussVo.getGaeaid() != null && disscussVo.getGaeaid() > 0) {
            topicVOBuilder.gaeaId(disscussVo.getGaeaid());
        }
        if (disscussVo.getAnonymousReply() != null) {
            topicVOBuilder.anonymousReply(disscussVo.getAnonymousReply());
        }
        if (disscussVo.getAnonymousSend() != null) {
            topicVOBuilder.anonymousSend(disscussVo.getAnonymousSend());
        }
        return topicVOBuilder.build();
    }
}
