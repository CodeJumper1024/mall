package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;

public interface GoodsService {
    BaseReqVo list(Integer page, Integer limit, String sort, String order, String name, Long goodsSn);
}
