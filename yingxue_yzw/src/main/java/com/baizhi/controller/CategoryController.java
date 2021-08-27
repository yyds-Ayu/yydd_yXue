package com.baizhi.controller;


import com.baizhi.dto.CategoryDTO;
import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @RequestMapping("/category/delete")
    public CommonVOa delete(@RequestBody Category category){

        return categoryService.delete(category);
    }
    @GetMapping("/category/queryById")
    public Category queryById(String id){

        return categoryService.queryById(id);
    }
    @RequestMapping("/category/update")
    public CommonVOa update(@RequestBody Category category){

        return categoryService.update(category);
    }
    @RequestMapping("/category/queryTwoPage")
    public CommonVO queryTwoPage(@RequestBody CategoryDTO category){

        return categoryService.queryTwoPage(category);
    }
    @RequestMapping("/category/queryByLevelsCategory")
    public List<Category> queryByLevelsCategory(Integer levels){

        return categoryService.queryByLevelsCategory(levels);
    }
}
