package com.baizhi.serviceimpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.FeedbackExample;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.UUIDUtil;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.util.UIUtil;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServicerImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public HashMap<String, Object> queryAllPage(Integer page,Integer pageSize) {
        HashMap<String,Object> map = new HashMap<>();
        //配置当前页面
        map.put("page",page);
        //设置查询条件对象
        FeedbackExample example = new FeedbackExample();
        //查询条数
        int count = userMapper.selectCountByExample(example);

        //设置总条数
        map.put("total",count);


        //创建分页查询  参数：起始条数，数据数
        RowBounds rowBounds = new RowBounds((page-1)*pageSize,pageSize);

        //根据分页查询数据
        List<User> feedbacks = userMapper.selectByExampleAndRowBounds(example, rowBounds);

        //设置数据
        map.put("rows",feedbacks);

        return map;
    }
    @AddLog(value = "修改用户状态")
    @Override
    public HashMap<String, Object> update(User user) {
        HashMap<String, Object> objectHashMap = new HashMap<>();
        try {
            User users = userMapper.selectOne(user);
            String headImg = users.getHeadImg();
            String replaces = headImg.replace("https://yingx-yyds.oss-cn-beijing.aliyuncs.com/","");
            AliyunOssUtil.deleteBucket("yingx-yyds",replaces);
            userMapper.updateByPrimaryKeySelective(user);
            objectHashMap.put("message","修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            objectHashMap.put("message","修改异常！");
        }
        return objectHashMap;
    }
    @AddLog(value = "删除用户管理")
    @Override
    public HashMap<String, Object> deleteUser(User user) {
        HashMap<String,Object> hashMap = new HashMap<>();
        //删除数据
        try {
            User users = userMapper.selectOne(user);
            String headImg = users.getHeadImg();
            String replaces = headImg.replace("https://yingx-yyds.oss-cn-beijing.aliyuncs.com/","");
            AliyunOssUtil.deleteBucket("yingx-yyds",replaces);
            userMapper.delete(user);
            hashMap.put("message","数据删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("message","删除数据异常！");
        }

        return hashMap;
    }

    @Override
    public CommonVO queryAllPages(Integer page, Integer pageSize) {

        //FeedbackExample example = new FeedbackExample();
        UserExample example = new UserExample();
        //查询条数
        int total = userMapper.selectCountByExample(example);

        //创建分页查询  参数：起始条数，数据数
        RowBounds rowBounds = new RowBounds((page-1)*pageSize,pageSize);

        //根据分页查询数据
        List<User> feedbacks = userMapper.selectByExampleAndRowBounds(example, rowBounds);

        return new CommonVO(page,total,feedbacks);
    }
    @AddLog(value = "修改用户管理")
    @Override
    public CommonVOa updates(User user) {
        //HashMap<String,Object> hashMap = new HashMap<>();
        //修改数据
        try {
            userMapper.updateByPrimaryKeySelective(user);
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
    @AddLog(value = "添加用户信息")
    @Override
    public CommonVOa add(User user) {
        try {
            user.setId(UUIDUtil.getUUID());
         /*   user.setStatus("1");
            user.setCreateTime(new Date());*/
            userMapper.insertSelective(user);
            return CommonVOa.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }

    @Override
    public String uploadHeadImg(MultipartFile headImg) {
        //获取文件名
        String filename = headImg.getOriginalFilename();
        //***-logo.jpg
        String newName = new Date().getTime() + "-" + filename;

        String bucketName = "yingx-yyds";//指定空间
        String FileName = "userImg/"+newName;//配置文件名

        AliyunOssUtil.uploadfileBytes(bucketName,FileName,headImg);

        String netPath="https://yingx-yyds.oss-cn-beijing.aliyuncs.com/"+FileName;

       /* String message = null;

        try {
            message = netPath;
        } catch (Exception e) {
            e.printStackTrace();
            message = netPath;
        }*/
        return netPath;
    }

    @Override
    public User queryById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
