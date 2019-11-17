package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public BaseReqVo list(Integer page, Integer limit, String sort, String order, Integer[] orderStatusArray, Integer userId, String orderSn) {
        BaseReqVo baseReqVo=new BaseReqVo();
        //分页工具
        PageHelper.startPage(page, limit);
        List<Order>orderList=null;
        orderList= orderMapper.listByExample(orderStatusArray,userId,orderSn);
        PageInfo<Order> orderPageInfo=new PageInfo<>(orderList);
        Long total =orderPageInfo.getTotal();
        Map<String,Object> orderMap=new HashMap<>();
        orderMap.put("total",total);
        orderMap.put("items",orderList);
        baseReqVo.setData(orderMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
