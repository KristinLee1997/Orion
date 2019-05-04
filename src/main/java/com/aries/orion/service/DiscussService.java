package com.aries.orion.service;

import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.orion.model.vo.DisscussVo;

import java.util.List;

public interface DiscussService {
    List<DisscussVo> getDiscussList(Integer page, Integer pageSize);

    DisscussVo getDiscussById(Long topicId);

    List<ReplyVO> getDiscussDetail(Long topicId, Integer page, Integer pageSize);

    Boolean addTopic(DisscussVo disscussVo);

    Boolean addReply(ReplyVO replyVO);
}
