package com.baizhi.dao;

import com.baizhi.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminDao{
    Admin queryByAdmin(String username);
}
