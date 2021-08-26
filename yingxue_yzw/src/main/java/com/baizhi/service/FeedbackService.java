package com.baizhi.service;

import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;


public interface FeedbackService {

    HashMap<String,Object> deleteFeedback(@RequestBody Feedback feedback);
    HashMap<String,Object> queryAllPage(Integer page,Integer pageSize);
}
