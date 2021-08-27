package com.baizhi.service;

import com.baizhi.dto.CategoryDTO;
import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;

import java.util.List;

public interface CategoryService {
    CommonVO queryOnePage(PageDTO pageDTO);

    CommonVOa add(Category category);

    CommonVOa delete(Category category);

    Category queryById(String id);

    CommonVOa update(Category category);

    CommonVO queryTwoPage(CategoryDTO categoryDTO);

    List<Category> queryByLevelsCategory(Integer levels);

}
