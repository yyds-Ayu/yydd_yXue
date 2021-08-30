package com.baizhi.serviceimpl;

import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.UUIDUtil;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoMapper videoMapper;

    @Override
    public CommonVO queryAllPages(Integer page, Integer pageSize) {
        //FeedbackExample example = new FeedbackExample();
        UserExample example = new UserExample();
        //查询条数
        int total = videoMapper.selectCountByExample(example);

        //创建分页查询  参数：起始条数，数据数
        RowBounds rowBounds = new RowBounds((page-1)*pageSize,pageSize);

        //根据分页查询数据
        List<Video> feedbacks = videoMapper.selectByExampleAndRowBounds(example, rowBounds);

        return new CommonVO(page,total,feedbacks);
    }

    @Override
    public CommonVOa update(Video video) {
        try {
            videoMapper.updateByPrimaryKeySelective(video);
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

    @Override
    public CommonVOa delete(Video video) {
        try {
            Video users = videoMapper.selectOne(video);
            String videoPath = users.getVideoPath();
            String videoPathName = videoPath.replace("https://yingx-yyds.oss-cn-beijing.aliyuncs.com/","");
            AliyunOssUtil.deleteBucket("yingx-yyds",videoPathName);
            videoMapper.delete(video);
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

    @Override
    public CommonVOa add(Video video) {
        try {
            video.setId(UUIDUtil.getUUID());
         /*   user.setStatus("1");
            user.setCreateTime(new Date());*/
            videoMapper.insertSelective(video);
            return CommonVOa.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }

    @Override
    public String uploadHeadImg(MultipartFile videoFile) {
        String filename = videoFile.getOriginalFilename();
        //文件名拼接唯一标识
        String newName = new Date().getTime() + "-" + filename;

        String objectName = "Video/"+newName;

        //拼接网络路径
        AliyunOssUtil.uploadfileBytes("yingx-yyds",objectName,videoFile);
        String netPath="https://yingx-yyds.oss-cn-beijing.aliyuncs.com/"+objectName;
        return netPath;
    }

    @Override
    public Video queryById(String id) {
        return videoMapper.selectByPrimaryKey(id);
    }
}
