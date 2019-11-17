package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.bean.GoodsProductExample;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.UserExample;

public interface DashboardService {

    long queryGoodsTotal(GoodsExample goodsExample);

    long queryUserTotal(UserExample userExample);

    long queryProductTotal(GoodsProductExample goodsProductExample);

    long queryOrderTotal(OrderExample orderExample);
}
