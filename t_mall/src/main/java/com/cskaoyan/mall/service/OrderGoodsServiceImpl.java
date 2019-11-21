package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderGoodsExample;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService{
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Override
    public List<OrderGoods> selectOrderGoodsByOrderId(Integer orderId) {
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
        return orderGoodsMapper.selectByExample(orderGoodsExample);
    }
}
