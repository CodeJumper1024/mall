package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;

public interface BrandService {
    BaseReqVo list(Integer page, Integer limit, String sort, String order);
}
