package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderSubmitCondition;

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
}
