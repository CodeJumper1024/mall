package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;
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

    @Override
    public BaseReqVo detailOrder(Integer id) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Order order = orderMapper.selectByPrimaryKey(id);
        List<OrderGoods> orderGoodsList=orderGoodsMapper.selectByOrderId(order.getId());
        User user=userMapper.selectByPrimaryKey(order.getUserId());
        Map<String,Object> map=new HashMap<>();
        map.put("orderGoods",orderGoodsList);
        map.put("user",user);
        map.put("order",order);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public Order selectOrderByOrderId(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int ship(Integer orderId, String shipChannel, String shipSn) {

        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)301);
        order.setShipChannel(shipChannel);
        order.setShipSn(shipSn);
        Date date = new Date();
        order.setShipTime(date);
        order.setUpdateTime(date);
        int update = orderMapper.ship(order);
        return update;
    }

}
