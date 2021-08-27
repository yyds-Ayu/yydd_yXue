package com.baizhi.service;

import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;
import com.baizhi.vo.CommonVO;
import com.baizhi.vo.CommonVOa;

import java.util.HashMap;

public interface AdminDaoService {
    HashMap<String,Object> login(Admin admin,String sCode);
    CommonVO queryAllPage(PageDTO pageDTO);

    CommonVOa updates(Admin admin);

    CommonVOa delete(Admin admin);

    CommonVOa add(Admin admin);

    Admin queryById(String id);

    CommonVOa update(Admin admin);
}
