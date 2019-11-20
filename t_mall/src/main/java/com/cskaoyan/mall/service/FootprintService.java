package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public interface FootprintService {
    List<Footprint> queryFootprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId);

    BaseReqVo listFootPrint(Integer page, Integer size);

    void insertFoot(Integer userId, Integer goodsId);

    BaseReqVo deleteFootPrint(Footprint footprint);
}
