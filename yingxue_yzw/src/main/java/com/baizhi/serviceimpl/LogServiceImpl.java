package com.baizhi.serviceimpl;

import com.baizhi.dao.LogMapper;
import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Log;
import com.baizhi.entity.LogExample;

import com.baizhi.service.LogService;
import com.baizhi.util.UUIDUtil;
import com.baizhi.vo.CommonVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Resource
    LogMapper logMapper;
    @Override
    public CommonVO queryAllPages(PageDTO pageDTO) {
        //FeedbackExample example = new FeedbackExample();
        LogExample example = new LogExample();

        example.setOrderByClause("option_time desc");
        //查询条数
        int total = logMapper.selectCountByExample(example);

        //创建分页查询  参数：起始条数，数据数
        RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(),pageDTO.getPageSize());

        //根据分页查询数据
        List<Log> feedbacks = logMapper.selectByExampleAndRowBounds(example, rowBounds);

        return  new CommonVO(pageDTO.getPage(),total,feedbacks);
    }

    @Override
    public void add(Log log) {
        log.setId(UUIDUtil.getUUID());
        logMapper.insertSelective(log);
    }
}
