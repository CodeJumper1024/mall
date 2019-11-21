package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OrderSubmitCondition;

import java.util.Map;

public interface WxOrderService {

    Map<String,Object> orderList(Integer showType,int page,int size);

    Map<String, Object> detail(Integer orderId);

    Integer submit (OrderSubmitCondition orderSubmitCondition);
}
