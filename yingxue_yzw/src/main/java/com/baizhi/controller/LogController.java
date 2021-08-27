package com.baizhi.controller;


import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import com.baizhi.service.LogService;
import com.baizhi.vo.CommonVO;
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
@RequestMapping("log")
public class LogController {

    //private static final Logger log = LoggerFactory.getLogger(LogController.class);
    @Resource
    LogService logService;


    @RequestMapping("queryAllPage")
    public CommonVO queryAllPage(@RequestBody PageDTO pageDTO) {

        return logService.queryAllPages(pageDTO);
    }

  /* @RequestMapping("delete")
    public HashMap<String,Object> deleteFeedback(@RequestBody Feedback feedback){

        HashMap<String, Object> map = feedbackService.deleteFeedback(feedback);

        return map;
    }*/
}
