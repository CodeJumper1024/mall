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
    public BaseReqVo goodsDetail(Integer goodsId) {

        HashMap<String, Object> dataMap = new HashMap<>();

        /*ArrayList<Map> specificationList = new ArrayList<>();
        HashMap<String, Object> specMap = new HashMap<>();
        String specName = wxGoodsService.querySpecNameByGoodsId(goodsId);
        List<GoodsSpecification> valueList = wxGoodsService.querySpecValuesByGoodsId(goodsId);
        specMap.put("name", specName);
        specMap.put("valueList", valueList);
        specificationList.add(specMap);
        dataMap.put("specificationList", specificationList);*/

        ArrayList<Map> specificationList = new ArrayList<>();
        List<String> names = wxGoodsService.querySpecNamesByGoodsId(goodsId);
        for (String name : names) {
            HashMap<String, Object> map = new HashMap<>();
            List<GoodsSpecification> valueList = wxGoodsService.querySpecValue(goodsId, name);
            map.put("name", name);
            map.put("valueList", valueList);
            specificationList.add(map);
        }

        List<GrouponRules> groupon = wxGoodsService.queryGrouponRules(goodsId);
        dataMap.put("groupon", groupon);

        List<Issue> issue = wxGoodsService.queryIssues();
        dataMap.put("issue", issue);

        dataMap.put("userHasCollect", 0);
        dataMap.put("shareImage", "");

        HashMap<String, Object> commentMap = new HashMap<>();
        List<Comment> commentData = wxGoodsService.queryCommentsByValueId(goodsId);
        int commentCount = wxGoodsService.queryCommentCountByValueId(goodsId);
        commentMap.put("data", commentData);
        commentMap.put("count", commentCount);
        dataMap.put("comment", commentMap);

        List<GoodsAttribute> attribute = wxGoodsService.queryAttributesByGoodsId(goodsId);
        dataMap.put("attribute", attribute);

        Brand brand = wxGoodsService.queryBrandByGoodsId(goodsId);
        dataMap.put("brand", brand);

        List<GoodsProduct> productList = wxGoodsService.queryProductsByGoodsId(goodsId);
        dataMap.put("productList", productList);

        Goods info = wxGoodsService.queryGoodsInfoByGoodsId(goodsId);
        dataMap.put("info", info);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
