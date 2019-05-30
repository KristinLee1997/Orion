package com.aries.orion.controller;

import com.aries.hermes.client.thrift.vo.ReplyVO;
import com.aries.orion.constants.SystemStatus;
import com.aries.orion.model.HttpResponse;
import com.aries.orion.model.vo.DisscussVo;
import com.aries.orion.service.DiscussService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/discuss")
@Slf4j
public class DiscussController {

    @Resource
    private DiscussService discussService;

    @GetMapping("/list")
    public ModelAndView discuss(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        List<DisscussVo> discussList = discussService.getDiscussList(page, pageSize);
        ModelAndView modelAndView = new ModelAndView("discuss");
        modelAndView.addObject("disscussvolist", discussList);
        return modelAndView;
    }

    @GetMapping("/count")
    @ResponseBody
    public Long getDiscussCount() {
        Long discussCount = discussService.getDiscussCount();
        if (discussCount <= 0) {
            log.warn("获取主帖总数失败，count{}", discussCount);
            return 0L;
        }
        return discussCount;
    }

    @GetMapping("/reply/count/{topicId}")
    public Long getReplyCount(@PathVariable("topicId") Long topicId) {
        if (topicId < 0) {
            log.warn("查询子帖总数操作失败，参数异常topicId{}", topicId);
            return 0L;
        }
        Long replyCount = discussService.getReplyCount(topicId);
        if (replyCount <= 0) {
            log.warn("获取子帖总数失败，count{}", replyCount);
            return 0L;
        }
        return replyCount;
    }

    @GetMapping("/{topicid}")
    public ModelAndView getdiscussDetail(@PathVariable("topicid") Long topicid) {
        ModelAndView modelAndView = new ModelAndView("discuss_reply");
        DisscussVo disscussVo = discussService.getDiscussById(topicid);
        List<ReplyVO> discussDetail = discussService.getDiscussDetail(topicid, 1, 20);
        modelAndView.addObject("discussvo", disscussVo);
        modelAndView.addObject("discussdetail", discussDetail);
        return modelAndView;
    }

    @GetMapping("/get/reply")
    public String getReplyTempalte() {
        return "reply-add";
    }

    @PostMapping("/add/topic")
    @ResponseBody
    public String addTopic(@RequestBody DisscussVo disscussVo) {
        Boolean res = discussService.addTopic(disscussVo);
        return HttpResponse.of(SystemStatus.SUCCESS);
    }

    @PostMapping("/add/reply")
    @ResponseBody
    public String addReply(@RequestBody ReplyVO replyVO) {
        Boolean res = discussService.addReply(replyVO);
        return HttpResponse.of(SystemStatus.SUCCESS);
    }
}
