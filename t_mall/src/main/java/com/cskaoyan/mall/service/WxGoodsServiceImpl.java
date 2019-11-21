package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    SearchHistoryMapper searchHistoryMapper;

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
        int brandId = goodsMapper.queryBrandIdById(id);
        Brand brand = brandMapper.queryBrandById(brandId);
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
        int categoryId = goodsMapper.queryCategoryIdById(id);
        List<Goods> relatedGoods = goodsMapper.queryRelatedGoods(categoryId);
        return relatedGoods;
    }

    @Override
    public int queryGoodsNum() {
        int goodsNum = goodsMapper.queryGoodsNum();
        return goodsNum;
    }

    @Override
    public Category queryCurrentCategory(Integer id) {
        String level = categoryMapper.queryLevelById(id);
        if ("L1".equals(level)) {
            Category currentCategory = categoryMapper.queryCurrentCategoryL1(id);
            return currentCategory;
        } else {
            Category currentCategory = categoryMapper.queryCurrentCategoryL2(id);
            return currentCategory;
        }
    }

    @Override
    public List<Category> queryBrotherCategory(Integer id) {
        String level = categoryMapper.queryLevelById(id);
        if ("L1".equals(level)) {
            List<Category> brotherCategory = categoryMapper.queryBrotherCategoryL1(id);
            return brotherCategory;
        } else {
            int pid = categoryMapper.queryPidById(id);
            List<Category> brotherCategory = categoryMapper.queryBrotherCategoryL2(pid);
            return brotherCategory;
        }
    }

    @Override
    public Category queryParentCategory(Integer id) {
        String level = categoryMapper.queryLevelById(id);
        if ("L1".equals(level)) {
            Category parentCategory = categoryMapper.queryParentCategoryL1(id);
            return parentCategory;
        } else {
            int pid = categoryMapper.queryPidById(id);
            Category parentCategory = categoryMapper.queryParentCategoryL2(pid);
            return parentCategory;
        }
    }

    @Override
    public List<Goods> queryGoods(String keyword, Integer page, Integer size, String sort, String order, Integer categoryId, Integer id, Boolean isNew, Boolean isHot) {
        PageHelper.startPage(page, size);
        List<Goods> goodsList = goodsMapper.queryGoods("%" + keyword + "%", categoryId, sort, order, isNew, isHot);
        if (keyword != null) {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String addTime = dateFormat.format(date);
            searchHistoryMapper.addToHistory(id, keyword, addTime);
        }
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
