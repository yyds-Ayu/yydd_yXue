package com.baizhi.serviceimpl;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.dto.CategoryDTO;
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
    @AddLog(value = "添加类别")
    @Override
    public CommonVOa add(Category category) {

        try {
            category.setId(UUIDUtil.getUUID());
            if (category.getParentId()!=null){
                category.setLevels(2);
            }else {
                category.setLevels(1);
            }
            categoryMapper.insertSelective(category);
            return CommonVOa.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }
    //@AddLog(value = "删除一级类别")
    @Override
    public CommonVOa delete(Category category) {

        if(category.getParentId()==null){
            CategoryExample example = new CategoryExample();
            example.createCriteria().andParentIdEqualTo(category.getId());

            int count = categoryMapper.selectCountByExample(example);
            if (count==0){
                    categoryMapper.delete(category);
                    return CommonVOa.success("删除一级类别成功！");
            }else {
                    return CommonVOa.gei("该类下有二级类别，删除失败！！！");

            }
        }else {
            categoryMapper.delete(category);
            return CommonVOa.success("二级类别删除成功！");

        }
    }
    @AddLog(value = "删除一级类别")
    @Override
    public String deletes(Category category) {

        String message=null;
        if(category.getParentId()==null){
            CategoryExample example = new CategoryExample();
            example.createCriteria().andParentIdEqualTo(category.getId());

            int count = categoryMapper.selectCountByExample(example);
            if (count==0){
                categoryMapper.delete(category);
                message ="删除一级类别成功";
                //return CommonVOa.success("删除一级类别成功！");
            }else {
                throw new RuntimeException("该类下有二级类别，删除失败！！！");
                //return CommonVOa.gei("该类下有二级类别，删除失败！！！");

            }
        }else {
            categoryMapper.delete(category);
            message ="二级类别删除成功！";
            //return CommonVOa.success("二级类别删除成功！");
        }
        return message;
    }

    @Override
    public Category queryById(String id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
    @AddLog(value = "修改一级类别")
    @Override
    public CommonVOa update(Category category) {
        try {
            categoryMapper.updateByPrimaryKeySelective(category);
            return CommonVOa.success("数据修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVOa.fei();
        }
    }

    @Override
    public CommonVO queryTwoPage(CategoryDTO categoryDTO) {

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andParentIdEqualTo(categoryDTO.getCategoryId());
        //根据条件查询一级类别的数量
        int total = categoryMapper.selectCountByExample(categoryExample);
        //RowBounds rowBounds = new RowBounds((pageDTO.getPage()-1)*pageDTO.getPageSize(), pageDTO.getPageSize());
        RowBounds rowBounds = new RowBounds((categoryDTO.getPage()-1)*categoryDTO.getPageSize(),categoryDTO.getPageSize());
        //分页查询数据
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(categoryExample,rowBounds);
       /* CommonVO commonVO = new CommonVO();
        commonVO.setPage(pageDTO.getPage());
        commonVO.setTotal(total);
        commonVO.setRows(categories);*/
        return new CommonVO(categoryDTO.getPage(),total,categories);
    }

    @Override
    public List<Category> queryByLevelsCategory(Integer levels) {

        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelsEqualTo(levels);

        List<Category> list = categoryMapper.selectByExample(example);
        return list;
    }
}
