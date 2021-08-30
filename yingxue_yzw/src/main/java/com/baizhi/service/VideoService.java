package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    CommonVO queryAllPages(Integer page, Integer pageSize);

    CommonVOa update(Video video);

    CommonVOa delete(Video video);

    CommonVOa add(Video video);

    String uploadHeadImg(MultipartFile videoFile);

    Video queryById(String id);
}
