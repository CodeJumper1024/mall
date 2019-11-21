package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderInfo;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Override
    public Map<String, Object> orderList(Integer showType, int page, int size) {
        //分页工具
        PageHelper.startPage(page, size);

        Map<String, Object> map = new HashMap<>();
        //根据showType获得对应的orderStatus
        //根据orderStatus去查找对应订单的信息
        //根据所找到的订单的id，查询团购活动表cskaoyan_mall_groupon获取是否isGroupin
        //根据order_id查询cskaoyan_mall_order_good获得商品信息
        //根据showType返回不同的handleOption(Map集合)

        //orderStatusArray=null的时候返回所有订单信息
        Integer[] orderStatusArray = null;
        if(showType == null){
            //查找全部订单信息
        }else if(showType == 1){
            //待付款
            orderStatusArray = new Integer[]{101,102,103};
        }else if(showType == 2){
            //待发货
            orderStatusArray = new Integer[]{201,202,203};
        }else if(showType == 3){
            //待收货
            orderStatusArray = new Integer[]{301};
        }else if(showType == 4){
            //待评价
            orderStatusArray = new Integer[]{401,402};
        }

        ArrayList<Object> dataList = new ArrayList<>();

        //根据orderStatus去查找对应订单的信息 获得当前用户id的接口还没写好 写死为1
        List<Order> orders = orderMapper.listByExample(orderStatusArray, 1, null);

        PageInfo<Order> orderPageInfo=new PageInfo<>(orders);
        int totalPages = orderPageInfo.getPages();
        Long count =orderPageInfo.getTotal();

        //七个订单处理的选项，下面对订单进行遍历，不同订单状态的订单提供不同的订单处理选项
        boolean cancel = false;
        boolean delete = false;
        boolean pay = false;
        boolean comment = false;
        boolean confirm = false;
        boolean refund = false;
        boolean rebuy = false;

        //对每一个订单进行遍历
        for (Order order : orders) {
            //每一个订单的遍历，用Map封装订单信息、是否团购的信息、订单对应的商品信息、订单操作
            HashMap<String, Object> dataMap = new HashMap<>();
            Integer id = order.getId();
            String orderSn = order.getOrderSn();
            BigDecimal actualPrice = order.getActualPrice();
            Short orderStatus = order.getOrderStatus();
            //对不同订单状态的订单给予不同的订单处理选项
            String orderStatusText = null;
            if(orderStatus == 101){
                orderStatusText = "待付款";
                cancel = true;
                pay = true;
            }else if(orderStatus == 201 || orderStatus == 202 || orderStatus == 203){
                orderStatusText = "待发货";
                refund = true;
            }else if(orderStatus == 301){
                orderStatusText = "待收货";
                confirm = true;
            }else if(orderStatus == 401 || orderStatus == 402){
                orderStatusText = "待评价";
                delete = true;
                comment = true;
                rebuy = true;
            }else if(orderStatus == 102 || orderStatus == 103){
                orderStatusText = "已取消";
                delete = true;
            }
            //根据订单id去查找团购信息
            boolean isGroupin = false;
            int i = grouponMapper.selectGrouponOrNotByOrderId(id);
            if(i != 0){
                //团购表里有该订单的信息，所以参加了参加了团购
                isGroupin = true;
            }
            //根据订单id去查找商品信息
            List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(id);

            //用Map集合封装订单处理的选项的信息
            HashMap<String, Boolean> handleOption = new HashMap<>();
            handleOption.put("cancel",cancel);
            handleOption.put("delete",delete);
            handleOption.put("pay",pay);
            handleOption.put("comment",comment);
            handleOption.put("confirm",confirm);
            handleOption.put("refund",refund);
            handleOption.put("rebuy",rebuy);

            //开始用Map集合封装订单信息、是否团购的信息、订单对应的商品信息、订单操作
            dataMap.put("id",id);
            dataMap.put("orderStatusText",orderStatusText);
            dataMap.put("isGroupin",isGroupin);
            dataMap.put("orderSn",orderSn);
            dataMap.put("actualPrice",actualPrice);
            dataMap.put("goodsList",orderGoods);
            dataMap.put("handleOption",handleOption);

            //每一个dataMap都封装进dataList里面
            dataList.add(dataMap);
        }

        map.put("data",dataList);
        map.put("count",count);
        map.put("totalPages",totalPages);
        return map;
    }

    @Override
    public Map<String, Object> detail(Integer orderId) {

        //dataMap用来封装所有信息
        HashMap<String, Object> dataMap = new HashMap<>();

        //七个订单处理的选项，下面对订单进行遍历，不同订单状态的订单提供不同的订单处理选项
        boolean cancel = false;
        boolean delete = false;
        boolean pay = false;
        boolean comment = false;
        boolean confirm = false;
        boolean refund = false;
        boolean rebuy = false;

        //根据orderId获得订单信息
        Order order = orderMapper.selectByPrimaryKey(orderId);

        String consignee = order.getConsignee();
        String address = order.getAddress();
        Date addTime = order.getAddTime();

        String orderSn = order.getOrderSn();
        BigDecimal actualPrice = order.getActualPrice();
        String mobile = order.getMobile();
        Short orderStatus = order.getOrderStatus();
        String orderStatusText = null;
        if(orderStatus == 101){
            orderStatusText = "待付款";
            cancel = true;
            pay = true;
        }else if(orderStatus == 201 || orderStatus == 202 || orderStatus == 203){
            orderStatusText = "待发货";
            refund = true;
        }else if(orderStatus == 301){
            orderStatusText = "待收货";
            confirm = true;
        }else if(orderStatus == 401 || orderStatus == 402){
            orderStatusText = "待评价";
            delete = true;
            comment = true;
            rebuy = true;
        }else if(orderStatus == 102 || orderStatus == 103){
            orderStatusText = "已取消";
            delete = true;
        }

        Map<String, Boolean> handleOption = new HashMap<>();
        handleOption.put("cancel",cancel);
        handleOption.put("delete",delete);
        handleOption.put("pay",pay);
        handleOption.put("comment",comment);
        handleOption.put("confirm",confirm);
        handleOption.put("refund",refund);
        handleOption.put("rebuy",rebuy);

        BigDecimal goodsPrice = order.getGoodsPrice();
        BigDecimal couponPrice = order.getCouponPrice();
        Integer id = order.getId();
        BigDecimal freightPrice = order.getFreightPrice();

        //用javabean OrderInfo封装订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setConsignee(consignee);
        orderInfo.setAddres(address);
        orderInfo.setAddTime(addTime);
        orderInfo.setOrderSn(orderSn);
        orderInfo.setActualPrice(actualPrice);
        orderInfo.setMobile(mobile);
        orderInfo.setOrderStatusText(orderStatusText);
        orderInfo.setGoodsPrice(goodsPrice);
        orderInfo.setCouponPrice(couponPrice);
        orderInfo.setId(id);
        orderInfo.setFreightPrice(freightPrice);
        orderInfo.setHandleOption(handleOption);

        //根据orderId获得订单商品信息
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(orderId);

        //最后把所有信息封装到dataMap
        dataMap.put("orderInfo",orderInfo);
        dataMap.put("orderGoods",orderGoods);
        return dataMap;
    }
}
