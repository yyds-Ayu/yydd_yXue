package com.baizhi.serviceimpl;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.AdminDao;
import com.baizhi.dto.PageDTO;
import com.baizhi.entity.*;
import com.baizhi.service.AdminDaoService;
import com.baizhi.util.UUIDUtil;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

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
    @Override
    public CommonVO queryAllPage(PageDTO pageDTO) {
        AdminExample adminExample = new AdminExample();
        //根据条件查询一级类别的数量
        int total = adminDao.selectCountByExample(adminExample);
        //RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(), pageDTO.getPageSize());
        RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(),pageDTO.getPageSize());
        //分页查询数据
        List<Admin> categories = adminDao.selectByExampleAndRowBounds(adminExample,rowBounds);
       /* CommonVO commonVO = new CommonVO();
        commonVO.setPage(pageDTO.getPage());
        commonVO.setTotal(total);
        commonVO.setRows(categories);*/
        return new CommonVO(pageDTO.getPage(),total,categories);
    }
    @AddLog(value = "管理员管理修改状态")
    @Override
    public CommonVOa updates(Admin admin) {
        //修改数据
        try {
            adminDao.updateByPrimaryKeySelective(admin);
            /*commonVOa.setMessage("数据修改成功");
            commonVOa.setStatus(200);*/
            return CommonVOa.success("数据修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            //hashMap.put("message","删除数据异常！");
          /*  commonVOa.setMessage("修改数据异常！");
            commonVOa.setStatus(400);*/
            return CommonVOa.fei();
        }
    }
    @AddLog(value = "管理员管理删除数据")
    @Override
    public CommonVOa delete(Admin admin) {
        try {
            adminDao.delete(admin);
            /*commonVOa.setMessage("数据修改成功");
            commonVOa.setStatus(200);*/
            return CommonVOa.success("数据删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            //hashMap.put("message","删除数据异常！");
          /*  commonVOa.setMessage("修改数据异常！");
            commonVOa.setStatus(400);*/
            return CommonVOa.fei();
        }
    }
    @AddLog(value = "添加管理员")
    @Override
    public CommonVOa add(Admin admin) {
        try {
            admin.setId(UUIDUtil.getUUID());
            adminDao.insertSelective(admin);
            return CommonVOa.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }

    @Override
    public Admin queryById(String id) {
        return adminDao.selectByPrimaryKey(id);
    }
    @AddLog(value = "管理员管理是修改")
    @Override
    public CommonVOa update(Admin admin) {

        try {
            adminDao.updateByPrimaryKeySelective(admin);
            return CommonVOa.success("数据修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }
}
