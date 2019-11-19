package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GrouponRules> queryGrouponRules(Integer goodsId) {
        List<GrouponRules> groupon = grouponRulesMapper.queryGrouponRulers(goodsId);
        return groupon;
    }

    @Override
    public List<Issue> queryIssues() {
        List<Issue> issue = issueMapper.queryIssues();
        return issue;
    }

    @Override
    public List<Comment> queryCommentsByValueId(Integer goodsId) {
        List<Comment> commentData = commentMapper.queryCommentsByValueId(goodsId);
        return commentData;
    }

    @Override
    public int queryCommentCountByValueId(Integer goodsId) {
        int commentCount = commentMapper.queryCommentCountByValueId(goodsId);
        return commentCount;
    }

    @Override
    public List<GoodsAttribute> queryAttributesByGoodsId(Integer goodsId) {
        List<GoodsAttribute> attribute = goodsAttributeMapper.selectByGoodsId(goodsId);
        return attribute;
    }

    @Override
    public Brand queryBrandByGoodsId(Integer goodsId) {
        Brand brand = brandMapper.queryBrandByGoodsId(goodsId);
        return brand;
    }

    @Override
    public List<GoodsProduct> queryProductsByGoodsId(Integer goodsId) {
        List<GoodsProduct> productList = goodsProductMapper.selectByGoodsId(goodsId);
        return productList;
    }

    @Override
    public Goods queryGoodsInfoByGoodsId(Integer goodsId) {
        Goods info = goodsMapper.queryGoodsInfoByGoodsId(goodsId);
        return info;
    }

    @Override
    public List<String> querySpecNamesByGoodsId(Integer goodsId) {
        List<String> names = goodsSpecificationMapper.querySpecNamesByGoodsId(goodsId);
        return names;
    }

    @Override
    public List<GoodsSpecification> querySpecValue(Integer goodsId, String name) {
        List<GoodsSpecification> valueList = goodsSpecificationMapper.querySpecValue(goodsId, name);
        return valueList;
    }
}
