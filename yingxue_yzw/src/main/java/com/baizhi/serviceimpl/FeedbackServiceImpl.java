package com.baizhi.serviceimpl;

import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Feedback;
import com.baizhi.entity.FeedbackExample;
import com.baizhi.service.FeedbackService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    FeedbackMapper feedbackMapper;

    @Override
    public HashMap<String, Object> deleteFeedback(Feedback feedback) {

        HashMap<String,Object> hashMap = new HashMap<>();
        //删除数据
        try {
            feedbackMapper.delete(feedback);

            hashMap.put("message","数据删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("message","删除数据异常！");
        }

        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryAllPage(Integer page, Integer pageSize) {

        HashMap<String,Object> hashMap = new HashMap<>();
        //配置当前页面
        hashMap.put("page",page);
        //设置查询条件对象
        FeedbackExample example = new FeedbackExample();
        //查询条数
        int count = feedbackMapper.selectCountByExample(example);

        //设置总条数
        hashMap.put("total",count);


        //创建分页查询  参数：起始条数，数据数
        RowBounds rowBounds = new RowBounds((page-1)*pageSize,pageSize);

        //根据分页查询数据
        List<Feedback> feedbacks = feedbackMapper.selectByExampleAndRowBounds(example, rowBounds);

        //设置数据
        hashMap.put("rows",feedbacks);

        return hashMap;
    }
}
