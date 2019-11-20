package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;

import java.util.List;

public interface WxGoodsService {

    List<GrouponRules> queryGrouponRules(Integer id);

    List<Issue> queryIssues();

    List<Comment> queryCommentsByValueId(Integer id);

    int queryCommentCountByValueId(Integer id);

    List<GoodsAttribute> queryAttributesByGoodsId(Integer id);

    Brand queryBrandByGoodsId(Integer id);

    List<GoodsProduct> queryProductsByGoodsId(Integer id);

    Goods queryGoodsInfoByGoodsId(Integer id);

    List<String> querySpecNamesByGoodsId(Integer id);

    List<GoodsSpecification> querySpecValue(Integer id, String name);

    List<Goods> queryRelatedGoods(Integer id);

    int queryGoodsNum();

    Category queryCurrentCategory(Integer id);

    List<Category> queryBrotherCategory(Integer id);

    Category queryParentCategory(Integer id);

    List<Goods> queryGoodsByCategoryId(Integer categoryId, Integer page, Integer size);

    int queryGoodsNumByCategoryId(Integer categoryId);

    List<Category> queryL2Categories();
}
