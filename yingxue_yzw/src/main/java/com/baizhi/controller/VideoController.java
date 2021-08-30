package com.baizhi.controller;

import com.baizhi.dto.PageDTO;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("video")
public class VideoController {
    @Resource
    VideoService videoService;

    @RequestMapping("queryAllPage")
    public CommonVO queryAllPage(@RequestBody PageDTO pageDTO){
        CommonVO commonVO = videoService.queryAllPages(pageDTO.getPage(), pageDTO.getPageSize());

        return commonVO;

    }
    @RequestMapping("update")
    public CommonVOa update(@RequestBody Video video){

        return videoService.update(video);

    }

    @RequestMapping("delete")
    public CommonVOa deleteUser(@RequestBody Video video){

        return videoService.delete(video);

    }
    @RequestMapping("add")
    public CommonVOa add(@RequestBody Video video){
        //HashMap<String, Object> hashMap = userService.deleteUser(user);

        return videoService.add(video);

    }
    @GetMapping("queryById")
    public Video queryById(String id){

        return videoService.queryById(id);
    }
    //@ResponseBody
    @RequestMapping("uploadHeadImg")
        public HashMap<String,Object> uploadHeadImg(MultipartFile videoFile){
        //HashMap<String, Object> hashMap = userService.deleteUser(user);
        String msg = videoService.uploadHeadImg(videoFile);
        HashMap<String, Object> HashMap = new HashMap<>();

        HashMap.put("filename",msg);
        return HashMap;

    }
}
