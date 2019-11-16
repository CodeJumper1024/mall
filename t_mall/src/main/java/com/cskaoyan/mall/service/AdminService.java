package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;

public interface AdminService {
    public BaseReqVo list(int page, int limit, String sort, String order);
}
