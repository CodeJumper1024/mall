package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    //分页获取商品
    @Override
    public BaseReqVo list(Integer page, Integer limit, String sort, String order) {
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        int total = goodsMapper.selectGoodsNum();
        //分页工具
        PageHelper.startPage(page, limit);
        List<Goods> goodsList = goodsMapper.selectAllGoods();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", goodsList);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
