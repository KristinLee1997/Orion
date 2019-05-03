package com.aries.orion.biz;

import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.orion.model.vo.DisscussVo;

import java.util.List;

public interface DiscussBiz {
    List<DisscussVo> getDiscussList(Integer page, Integer pageSize);

    DisscussVo getDiscussById(Long topicId);

    List<ReplyVO> getDiscussDetail(Long topicId, Integer page, Integer pageSize);
}
