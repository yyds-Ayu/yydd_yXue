package com.baizhi.service;

import com.baizhi.dto.PageDTO;
import com.baizhi.entity.Log;
import com.baizhi.vo.CommonVO;

public interface LogService {
    CommonVO queryAllPages(PageDTO pageDTO);
    void add(Log log);
}
