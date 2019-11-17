package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public interface FootprintService {
    List<Footprint> queryFootprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId);
}
