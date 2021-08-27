package com.baizhi.controller;

import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminDaoService;
import com.baizhi.util.ImageCodeUtil;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@CrossOrigin //处理跨域
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    AdminDaoService ad;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
   @PostMapping("getImageCodes")
    public String getImageCodes(HttpServletRequest request) {
       //1.获取验证码随机字符
       String securityCode = ImageCodeUtil.getSecurityCode();
       log.info("验证码：",securityCode);
       //2.存储验证码随机字符
       request.getServletContext().setAttribute("securityCode",securityCode);
       //3.生成验证码图片转为Base64格式
       //4.将图片响应前台
       String s64 = null;
       try {
            s64 = ImageCodeUtil.careateImgBase64(securityCode);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return s64;
   }

    @PostMapping("login")
    public HashMap<String, Object> login(@RequestBody Admin admin, String sCode){
       log.info("admin:",admin);
       log.info("sCode:",sCode);

        HashMap<String, Object> map = ad.login(admin, sCode);

        return map;
   }
   @PostMapping("logout")
    public void logout(HttpServletRequest request){
       log.info("退出登录");

       //移除登录标记
       request.getServletContext().removeAttribute("admin");

   }
   @RequestMapping("queryAllPage")
   public CommonVO queryAllPage(@RequestBody PageDTO pageDTO){

       return ad.queryAllPage(pageDTO);
   }
    @RequestMapping("update")
    public CommonVOa update(@RequestBody Admin admin){

        return ad.updates(admin);

    }

    @RequestMapping("delete")
    public CommonVOa delete(@RequestBody Admin admin){
        //HashMap<String, Object> hashMap = ad.deleteUser(user);

        return ad.delete(admin);

    }
    @RequestMapping("add")
    public CommonVOa add(@RequestBody Admin admin){
        //HashMap<String, Object> hashMap = ad.deleteUser(user);

        return ad.add(admin);

    }
    @RequestMapping("queryById")
    public Admin queryById(String id){

        return ad.queryById(id);

    }
    @RequestMapping("updates")
    public CommonVOa updates(@RequestBody Admin admin){

        return ad.update(admin);

    }
}
