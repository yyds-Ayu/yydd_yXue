package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.entity.Feedback;
import com.baizhi.entity.User;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;
import java.util.HashMap;

public interface UserService {
    HashMap<String,Object> queryAllPage(Integer page,Integer pageSize);

    HashMap<String,Object> update(User user);

    HashMap<String,Object> deleteUser(@RequestBody User user);

    CommonVO queryAllPages(Integer page, Integer pageSize);

    CommonVOa updates(User user);

    CommonVOa add(User user);

    String uploadHeadImg(MultipartFile headImg);

    User queryById(String id);
}
