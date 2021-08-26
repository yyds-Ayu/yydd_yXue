package com.baizhi.controller;


import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("feedback")
public class FeedbackController {

    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);
    @Resource
    FeedbackService feedbackService;


    @RequestMapping("queryAllPage")
    public HashMap<String,Object> queryAllPage(Integer page, Integer pageSize){

        HashMap<String, Object> map = feedbackService.queryAllPage(page,pageSize);

        log.info("当前页",page);
        log.info("展示页数 ",pageSize);

        return map;
    }

   @RequestMapping("delete")
    public HashMap<String,Object> deleteFeedback(@RequestBody Feedback feedback){

        HashMap<String, Object> map = feedbackService.deleteFeedback(feedback);

        return map;
    }
}
