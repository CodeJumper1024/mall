package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;

import java.util.List;

public interface WxGoodsService {

    List<GrouponRules> queryGrouponRules(Integer goodsId);

    List<Issue> queryIssues();

    List<Comment> queryCommentsByValueId(Integer goodsId);

    int queryCommentCountByValueId(Integer goodsId);

    List<GoodsAttribute> queryAttributesByGoodsId(Integer goodsId);

    Brand queryBrandByGoodsId(Integer goodsId);

    List<GoodsProduct> queryProductsByGoodsId(Integer goodsId);

    Goods queryGoodsInfoByGoodsId(Integer goodsId);

    List<String> querySpecNamesByGoodsId(Integer goodsId);

    List<GoodsSpecification> querySpecValue(Integer goodsId, String name);
}
