package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<GrouponRules> queryGrouponRules(Integer id) {
        List<GrouponRules> groupon = grouponRulesMapper.queryGrouponRulers(id);
        return groupon;
    }

    @Override
    public List<Issue> queryIssues() {
        List<Issue> issue = issueMapper.queryIssues();
        return issue;
    }

    @Override
    public List<Comment> queryCommentsByValueId(Integer id) {
        List<Comment> commentData = commentMapper.queryCommentsByValueId(id);
        return commentData;
    }

    @Override
    public int queryCommentCountByValueId(Integer id) {
        int commentCount = commentMapper.queryCommentCountByValueId(id);
        return commentCount;
    }

    @Override
    public List<GoodsAttribute> queryAttributesByGoodsId(Integer id) {
        List<GoodsAttribute> attribute = goodsAttributeMapper.selectByGoodsId(id);
        return attribute;
    }

    @Override
    public Brand queryBrandByGoodsId(Integer id) {
        Brand brand = brandMapper.queryBrandByGoodsId(id);
        return brand;
    }

    @Override
    public List<GoodsProduct> queryProductsByGoodsId(Integer id) {
        List<GoodsProduct> productList = goodsProductMapper.selectByGoodsId(id);
        return productList;
    }

    @Override
    public Goods queryGoodsInfoByGoodsId(Integer id) {
        Goods info = goodsMapper.queryGoodsInfoByGoodsId(id);
        return info;
    }

    @Override
    public List<String> querySpecNamesByGoodsId(Integer id) {
        List<String> names = goodsSpecificationMapper.querySpecNamesByGoodsId(id);
        return names;
    }

    @Override
    public List<GoodsSpecification> querySpecValue(Integer id, String name) {
        List<GoodsSpecification> valueList = goodsSpecificationMapper.querySpecValue(id, name);
        return valueList;
    }

    @Override
    public List<Goods> queryRelatedGoods(Integer id) {
        List<Goods> relatedGoods = goodsMapper.queryRelatedGoods(id);
        return relatedGoods;
    }

    @Override
    public int queryGoodsNum() {
        int goodsNum = goodsMapper.queryGoodsNum();
        return goodsNum;
    }

    @Override
    public Category queryCurrentCategory(Integer id) {
        Category currentCategory = categoryMapper.queryCurrentCategory(id);
        return currentCategory;
    }

    @Override
    public List<Category> queryBrotherCategory(Integer id) {
        int pid = categoryMapper.queryPidById(id);
        List<Category> brotherCategory = categoryMapper.queryBrotherCategory(pid);
        return brotherCategory;
    }

    @Override
    public Category queryParentCategory(Integer id) {
        int pid = categoryMapper.queryPidById(id);
        Category parentCategory = categoryMapper.queryParentCategory(pid);
        return parentCategory;
    }

    @Override
    public List<Goods> queryGoods(String keyword, Integer page, Integer size, String sort, String order, Integer categoryId) {
        PageHelper.startPage(page, size);
        List<Goods> goodsList = goodsMapper.queryGoods("%" + keyword + "%", categoryId, sort, order);
        return goodsList;
    }

    @Override
    public List<Integer> queryCategoryIds(String keyword) {
        List<Integer> cidList = goodsMapper.queryCategoryIds("%" + keyword + "%");
        Set set = new HashSet();
        List list = new ArrayList();
        set.addAll(cidList);
        list.addAll(set);
        return list;
    }
}
