package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.WxGoodsService;
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

    @RequestMapping("detail")
    public BaseReqVo goodsDetail(Integer id) {

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
}
