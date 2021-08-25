package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.HashMap;

public interface AdminDaoService {
    HashMap<String,Object> login(Admin admin,String sCode);

}
