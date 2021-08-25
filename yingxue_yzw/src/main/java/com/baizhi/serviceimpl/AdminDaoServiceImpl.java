package com.baizhi.serviceimpl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
@Service
@Transactional
public class AdminDaoServiceImpl implements AdminDaoService {

    private static final Logger log = LoggerFactory.getLogger(AdminDaoServiceImpl.class);
    @Resource
    AdminDao adminDao;
    @Resource
    HttpServletRequest request;

    @Override
    public HashMap<String, Object> login(Admin admin, String sCode) {
        //1.获取验证码
        String code = (String) request.getServletContext().getAttribute("securityCode");

        log.info("输入验证码：", sCode);
        log.info("获取验证码：", code);

        HashMap<String, Object> hashMap = new HashMap<>();
        //2.获取判读验证码是否一致
        if (code.equals(sCode)) {
            //3.判断是否存在
            Admin admin1 = adminDao.queryByAdmin(admin.getUsername());
            if (admin1 != null) {
                //4.判断用户状态是否正常
                if (admin1.getStatus().equals("1")) {
                    //存储用户标记
                    if (admin.getPassword().equals(admin1.getPassword())) {
                        request.getServletContext().setAttribute("admin",admin1);

                        hashMap.put("message", admin);
                        hashMap.put("status", 200);
                    } else {
                        hashMap.put("message", "密码错误！");
                        hashMap.put("status", 400);
                    }
                } else {
                    hashMap.put("message", "该用户已停封！");
                    hashMap.put("status", 400);
                }
                //5.判断密码是否正确
            } else {
                hashMap.put("message", "该用户不存在");
                hashMap.put("status", 400);
            }
        } else {
            hashMap.put("message", "验证码不正确");
            hashMap.put("status", 400);
        }


        return hashMap;
    }
}
