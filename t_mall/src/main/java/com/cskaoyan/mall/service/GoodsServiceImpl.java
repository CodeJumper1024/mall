package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public BaseReqVo list(Integer page, Integer limit, String sort, String order, String name, Long goodsSn) {
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        //分页工具
        PageHelper.startPage(page, limit);
        //根据条件查询商品
        List<Goods> goodsList = goodsMapper.selectGoodsByQueryCondition(sort, order, "%" + name + "%", goodsSn);
        //获取查询数目
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", goodsList);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
