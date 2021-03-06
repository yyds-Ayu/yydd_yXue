package com.baizhi.controller;


import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.service.FeedbackService;
import com.baizhi.service.UserService;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;


   /* @RequestMapping("queryAllPage")
    public HashMap<String,Object> queryAllPage(Integer page, Integer pageSize){
        HashMap<String, Object> map = userService.queryAllPage(page,pageSize);

        return map;

    }*/
   /* @RequestMapping("queryAllPage/{page}/{pageSize}")
    public HashMap<String,Object> queryAllPage(@PathVariable("page") Integer pages, @PathVariable("pageSize") Integer pageSize){
        HashMap<String, Object> map = userService.queryAllPage(pages,pageSize);

        return map;

    }*/

    /* @RequestMapping("update")
    public HashMap<String,Object> update(@RequestBody User user){


        return userService.update(user);

    }*/
    @RequestMapping("queryAllPage")
    public CommonVO queryAllPage(@RequestBody PageDTO pageDTO){
        CommonVO commonVO = userService.queryAllPages(pageDTO.getPage(), pageDTO.getPageSize());

        return commonVO;

    }
    @RequestMapping("update")
    public CommonVOa update(@RequestBody User user){

        return userService.updates(user);

    }

    @RequestMapping("delete")
    public HashMap<String,Object> deleteUser(@RequestBody User user){
        HashMap<String, Object> hashMap = userService.deleteUser(user);

        return hashMap;

    }
    @RequestMapping("add")
    public CommonVOa add(@RequestBody User user){
        //HashMap<String, Object> hashMap = userService.deleteUser(user);

        return userService.add(user);

    }
    @RequestMapping("uploadHeadImg")
    public HashMap<String, String> uploadHeadImg(MultipartFile headImg){
        //HashMap<String, Object> hashMap = userService.deleteUser(user);
        String msg = userService.uploadHeadImg(headImg);
        HashMap<String, String> HashMap = new HashMap<>();

        HashMap.put("fileName",msg);
        return HashMap;

    }
    @GetMapping("queryById")
    public User queryById(String id){

        return userService.queryById(id);
    }

}
