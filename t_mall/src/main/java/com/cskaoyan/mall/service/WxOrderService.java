package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderSubmitCondition;

import java.util.List;
import java.util.Map;

public interface WxOrderService {

    Map<String,Object> orderList(Integer showType,int page,int size);

    Map<String, Object> detail(Integer orderId);

    Integer submit (OrderSubmitCondition orderSubmitCondition);

    int prepay(String orderId);

    int refund(String orderId);

    int cancel(String orderId);

    int confirm(String orderId);

    int delete(String orderId);

    OrderGoods commentGoods(Integer orderId, Integer goodsId);

    void commitComment(Integer orderGoodsId, String content, Integer star, Boolean hasPicture, Integer id);
}
