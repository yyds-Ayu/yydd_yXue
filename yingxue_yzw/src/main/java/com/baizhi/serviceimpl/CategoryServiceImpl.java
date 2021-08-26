package com.baizhi.serviceimpl;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.CategoryService;
import com.baizhi.util.UUIDUtil;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;
    @Override
    public CommonVO queryOnePage(PageDTO pageDTO) {
        //创建条件对象
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelsEqualTo(1);
        //根据条件查询一级类别的数量
        int total = categoryMapper.selectCountByExample(categoryExample);
        //RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(), pageDTO.getPageSize());
        RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(),pageDTO.getPageSize());
        //分页查询数据
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(categoryExample,rowBounds);
       /* CommonVO commonVO = new CommonVO();
        commonVO.setPage(pageDTO.getPage());
        commonVO.setTotal(total);
        commonVO.setRows(categories);*/
        return new CommonVO(pageDTO.getPage(),total,categories);
    }

    @Override
    public CommonVOa add(Category category) {

        try {
            category.setId(UUIDUtil.getUUID());
            category.setLevels(1);
            categoryMapper.insertSelective(category);
            return CommonVOa.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }
}
