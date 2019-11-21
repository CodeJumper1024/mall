package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxHomeServiceImpl implements WxHomeService{

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    AdMapper adMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CouponMapper couponMapper;


    @Override
    public BaseReqVo index() {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> dataMap = new HashMap<>();
        List<Goods> newGoodsList = goodsMapper.selectNewGoods();
        List<Goods> hotGoodsList = goodsMapper.selectHotGoods();
        List<Coupon> couponList = couponMapper.selectAll();
        List<Brand> brandList = brandMapper.selectAllBrandNoParm();
        List<Ad> banner = adMapper.selectAllAd();
        ArrayList<Map> grouponList = new ArrayList<>();
        List<GrouponRules> rules = grouponRulesMapper.selectAllRule();
        for (GrouponRules rule : rules) {
            HashMap<String, Object> mapForGrouponList = new HashMap<>();
            int groupon_price = -grouponRulesMapper.selectDiscountById(rule.getId());
            Goods goods = goodsMapper.selectByPrimaryKey(rule.getGoodsId());
            int groupon_member = grouponMapper.selectGrouponMemberByRuleId(rule.getId());
            mapForGrouponList.put("groupon_price", groupon_price);
            mapForGrouponList.put("goods", goods);
            mapForGrouponList.put("groupon_member", groupon_member);
            grouponList.add(mapForGrouponList);
        }
        List<Category> channel = categoryMapper.selectAllLeve1();
        ArrayList<Map> floorGoodsList = new ArrayList<>();
        for (Category category : channel) {
            HashMap<String, Object> mapForFloorGoodsList = new HashMap<>();
            String name = category.getName();
            Integer id = category.getId();
            List<Goods> goodsList = goodsMapper.selectByCategoryId(category.getId());
            mapForFloorGoodsList.put("name", name);
            mapForFloorGoodsList.put("id", id);
            mapForFloorGoodsList.put("goodsList", goodsList);
            floorGoodsList.add(mapForFloorGoodsList);
        }
        dataMap.put("newGoodsList",newGoodsList);
        dataMap.put("couponList", couponList);
        dataMap.put("channel", channel);
        dataMap.put("grouponList", grouponList);
        dataMap.put("banner", banner);
        dataMap.put("brandList", brandList);
        dataMap.put("hotGoodsList", hotGoodsList);
        dataMap.put("floorGoodsList", floorGoodsList);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(dataMap);
        return baseReqVo;
    }
}
