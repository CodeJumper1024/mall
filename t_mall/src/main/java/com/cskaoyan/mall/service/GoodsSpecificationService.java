package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

public interface GoodsSpecificationService {
    List<GoodsSpecification> selectGoodsSpecificationByGoodsId(Integer goodsId);
}
