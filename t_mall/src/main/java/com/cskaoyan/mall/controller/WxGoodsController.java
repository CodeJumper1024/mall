package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.CategoryService;
import com.cskaoyan.mall.service.FootprintService;
import com.cskaoyan.mall.service.WxGoodsService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    WxGoodsService wxGoodsService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FootprintService footprintService;

    @RequestMapping("detail")
    public BaseReqVo goodsDetail(Integer id) {
        Footprint footprint=new Footprint();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        footprint.setUserId(user.getId());
        footprint.setGoodsId(id);
        footprintService.insertFoot(footprint.getUserId(),footprint.getGoodsId());
        HashMap<String, Object> dataMap = new HashMap<>();
        ArrayList<Map> specificationList = new ArrayList<>();
        List<String> names = wxGoodsService.querySpecNamesByGoodsId(id);
        for (String name : names) {
            HashMap<String, Object> map = new HashMap<>();
            List<GoodsSpecification> valueList = wxGoodsService.querySpecValue(id, name);
            map.put("name", name);
            map.put("valueList", valueList);
            specificationList.add(map);
        }
        dataMap.put("specificationList", specificationList);

        List<GrouponRules> groupon = wxGoodsService.queryGrouponRules(id);
        dataMap.put("groupon", groupon);

        List<Issue> issue = wxGoodsService.queryIssues();
        dataMap.put("issue", issue);

        dataMap.put("userHasCollect", 0);
        dataMap.put("shareImage", "");

        HashMap<String, Object> commentMap = new HashMap<>();
        List<Comment> commentData = wxGoodsService.queryCommentsByValueId(id);
        int commentCount = wxGoodsService.queryCommentCountByValueId(id);
        commentMap.put("data", commentData);
        commentMap.put("count", commentCount);
        dataMap.put("comment", commentMap);

        List<GoodsAttribute> attribute = wxGoodsService.queryAttributesByGoodsId(id);
        dataMap.put("attribute", attribute);

        Brand brand = wxGoodsService.queryBrandByGoodsId(id);
        dataMap.put("brand", brand);

        List<GoodsProduct> productList = wxGoodsService.queryProductsByGoodsId(id);
        dataMap.put("productList", productList);

        Goods info = wxGoodsService.queryGoodsInfoByGoodsId(id);
        dataMap.put("info", info);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    @RequestMapping("related")
    public BaseReqVo goodsRelated(Integer id) {
        List<Goods> relatedGoods = wxGoodsService.queryRelatedGoods(id);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("goodsList", relatedGoods);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    @RequestMapping("count")
    public BaseReqVo goodsCount() {
        int goodsNum = wxGoodsService.queryGoodsNum();

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("goodsCount", goodsNum);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    @RequestMapping("category")
    public BaseReqVo goodsCategory(Integer id) {

        Category currentCategory = wxGoodsService.queryCurrentCategory(id);
        List<Category> brotherCategory = wxGoodsService.queryBrotherCategory(id);
        Category parentCategory = wxGoodsService.queryParentCategory(id);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("currentCategory", currentCategory);
        dataMap.put("brotherCategory", brotherCategory);
        dataMap.put("parentCategory", parentCategory);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    @RequestMapping("list")
    public BaseReqVo goodsList(String keyword, Integer page, Integer size, String sort, String order, Integer categoryId) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<Goods> goodsList = wxGoodsService.queryGoods(keyword, page, size, sort, order, categoryId, user.getId());

        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        long total = goodsPageInfo.getTotal();

        int count = (int) total;

        List<Category> filterCategoryList = new ArrayList<>();
        List<Integer> cidList = wxGoodsService.queryCategoryIds(keyword);
        for (Integer cid : cidList) {
            Category category = categoryService.queryCategoriesByCid(cid);
            filterCategoryList.add(category);
        }

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("goodsList", goodsList);
        dataMap.put("count", count);
        dataMap.put("filterCategoryList", filterCategoryList);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
