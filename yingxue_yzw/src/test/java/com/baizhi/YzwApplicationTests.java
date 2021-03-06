package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;
import com.baizhi.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class YzwApplicationTests {
    @Resource
    FeedbackMapper feedbackMapper;
    @Resource
    AdminDao adminDao;
    @Resource
    CategoryMapper categoryMapper;
    @Test
    void contextLoads() {
        /*Admin kkk = adminDao.queryByAdmin("hhh");
        System.out.println(kkk);*/
       /* List<Feedback> feedbacks = feedbackMapper.selectAll();
        for (Feedback feedback : feedbacks) {
            System.out.println(feedback);
        }
*/
        List<Category> categories = categoryMapper.selectAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
