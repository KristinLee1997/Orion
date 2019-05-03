package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.orion.biz.DiscussBiz;
import com.aries.orion.model.vo.DisscussVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class DiscussController {

    @Resource
    private DiscussBiz discussBiz;

    @GetMapping("/discuss/list")
    public ModelAndView discuss() {
        List<DisscussVo> discussList = discussBiz.getDiscussList(1, 20);
        ModelAndView modelAndView = new ModelAndView("discuss");
        modelAndView.addObject("disscussvolist", discussList);
        return modelAndView;
    }

    @GetMapping("/discuss/{topicid}")
    public ModelAndView getdiscussDetail(@PathVariable("topicid") Long topicid) {
        System.out.println("TopicId: " + topicid);
        ModelAndView modelAndView = new ModelAndView("discuss_reply");
        DisscussVo disscussVo = discussBiz.getDiscussById(topicid);
        List<ReplyVO> discussDetail = discussBiz.getDiscussDetail(topicid, 1, 20);
        System.out.println(discussDetail);
        modelAndView.addObject("discussvo", disscussVo);
        modelAndView.addObject("discussdetail", discussDetail);
        System.out.println(JSON.toJSONString(disscussVo));
        return modelAndView;
    }
}
