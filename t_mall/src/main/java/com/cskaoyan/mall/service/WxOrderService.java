package com.cskaoyan.mall.service;

import java.util.Map;

public interface WxOrderService {

    Map<String,Object> orderList(Integer showType,int page,int size);

    Map<String, Object> detail(Integer orderId);
}
