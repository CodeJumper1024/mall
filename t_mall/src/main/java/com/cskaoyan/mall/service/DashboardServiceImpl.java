package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.GoodsProductExample;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public long queryGoodsTotal(GoodsExample goodsExample) {
        long goodsTotal = goodsMapper.countByExample(goodsExample);
        return goodsTotal;
    }

    @Override
    public long queryUserTotal(UserExample userExample) {
        long userTotal = userMapper.countByExample(userExample);
        return userTotal;
    }

    @Override
    public long queryProductTotal(GoodsProductExample goodsProductExample) {
        long productTotal = goodsProductMapper.countByExample(goodsProductExample);
        return productTotal;
    }

    @Override
    public long queryOrderTotal(OrderExample orderExample) {
        long orderTotal = orderMapper.countByExample(orderExample);
        return orderTotal;
    }
}
