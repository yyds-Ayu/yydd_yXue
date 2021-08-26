package com.baizhi.service;

import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;

public interface CategoryService {
    CommonVO queryOnePage(PageDTO pageDTO);
    CommonVOa add(Category category);
}
