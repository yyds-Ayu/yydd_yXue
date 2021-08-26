package com.baizhi.controller;


import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @RequestMapping("/category/queryOnePage")
    public CommonVO queryOnePage(@RequestBody PageDTO pageDTO){

        return categoryService.queryOnePage(pageDTO);
    }
    @RequestMapping("/category/add")
    public CommonVOa add(@RequestBody Category category){

        return categoryService.add(category);
    }
}
