package com.aries.orion.controller;

import com.alibaba.fastjson.JSON;
import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.orion.service.DiscussService;
import com.aries.orion.model.vo.DisscussVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/discuss")
public class DiscussController {

    @Resource
    private DiscussService discussService;

    @GetMapping("/list")
    public ModelAndView discuss() {
        List<DisscussVo> discussList = discussService.getDiscussList(1, 20);
        ModelAndView modelAndView = new ModelAndView("discuss");
        modelAndView.addObject("disscussvolist", discussList);
        return modelAndView;
    }

    @GetMapping("/{topicid}")
    public ModelAndView getdiscussDetail(@PathVariable("topicid") Long topicid) {
        System.out.println("TopicId: " + topicid);
        ModelAndView modelAndView = new ModelAndView("discuss_reply");
        DisscussVo disscussVo = discussService.getDiscussById(topicid);
        List<ReplyVO> discussDetail = discussService.getDiscussDetail(topicid, 1, 20);
        System.out.println(discussDetail);
        modelAndView.addObject("discussvo", disscussVo);
        modelAndView.addObject("discussdetail", discussDetail);
        System.out.println(JSON.toJSONString(disscussVo));
        return modelAndView;
    }
}
