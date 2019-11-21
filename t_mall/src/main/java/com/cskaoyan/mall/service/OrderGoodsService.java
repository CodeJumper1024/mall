package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public interface OrderGoodsService {
    List<OrderGoods> selectOrderGoodsByOrderId(Integer orderId);
}
