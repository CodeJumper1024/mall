package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.input.DataFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    SystemMapper systemMapper;

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
            orderStatusArray = new Integer[]{101};
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

        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        Integer userId = principal.getId();

        List<Order> orders = orderMapper.listByExample(orderStatusArray, userId, null);

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
        orderInfo.setAddress(address);
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

    @Override
    public Integer submit(OrderSubmitCondition orderSubmitCondition) {

        Integer addressId = orderSubmitCondition.getAddressId();
        Integer couponId = orderSubmitCondition.getCouponId();
        Integer grouponRulesId = orderSubmitCondition.getGrouponRulesId();
        String message = orderSubmitCondition.getMessage();

        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        Integer userId = principal.getId();

        //根据addressId获得收货人、地址、电话的等信息(cskaoyan_mall_address)往订单(cskaoyan_mall_order)写入获得的发货人、地址、电话的等信息
        Address ad = addressMapper.selectByPrimaryKey(addressId);
        String consignee = ad.getName();
        String address = ad.getAddress();
        String mobile = ad.getMobile();

        //从购物车表中获得下单信息
        List<Cart> carts = null;
        Integer cartId = orderSubmitCondition.getCartId();
        if(cartId != 0){
            //立即购买下单

            //根据cartId去购物车表(cskaoyan_mall_cart)中查找提交到订单的信息,后续无需逻辑删除
            carts =  cartMapper.selectByCartId(cartId);
        }else{
            //添加购物车进行下单

            //根据当前用户id获得购物车中checked = 1的信息(cskaoyan_mall_cart) 获得提交到订单的商品信息
            carts = cartMapper.selectByUserIdAndChecked(userId);

            //逻辑删除购物车表中checked = 1的商品(逻辑删除已经提交到订单的商品)
            cartMapper.deleteGoodsSubmitted();
        }

        //把商品总价计算出来  price * number 把商品总价写入订单(cskaoyan_mall_order)
        BigDecimal goodsPrice = new BigDecimal(0);
        for (Cart cart : carts) {
            BigDecimal price = cart.getPrice();
            BigDecimal number = new BigDecimal(cart.getNumber().toString());
            goodsPrice = goodsPrice.add(price.multiply(number));
        }

        //去找com_cskaoyan_system查找 cskaoyan_mall_express_freight_min 和 cskaoyan_mall_express_freight_value 字段
        int freight_min = systemMapper.selectByName("cskaoyan_mall_express_freight_min");
        int freight_value = systemMapper.selectByName("cskaoyan_mall_express_freight_value");
        BigDecimal freightPrice = new BigDecimal(0);
        if(goodsPrice.intValue() < freight_min){
            //商品总价商品总价小于cskaoyan_mall_express_freight_min 要给邮费
            freightPrice = new BigDecimal(freight_value);
        }

        //根据couponId查找优惠信息discount(cskaoyan_mall_coupon) 写入订单(cskaoyan_mall_order)
        Coupon coupon = null;
        BigDecimal couponPrice = new BigDecimal(0);
        if(couponId != 0 && couponId != -1){
            //有优惠券
            coupon = couponMapper.selectByPrimaryKey(couponId);
            couponPrice = coupon.getDiscount();
        }

        //积分不搞
        BigDecimal integralPrice =  new BigDecimal(0);

        //根据grouponRulesId查找团购信息discount(cskaoyan_mall_groupon_rules) 写入订单(cskaoyan_mall_order)
        GrouponRules grouponRules = null;
        BigDecimal grouponPrice = new BigDecimal(0);
        if(grouponRulesId != 0){
            //有团购活动
            grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRulesId);
            grouponPrice = grouponRules.getDiscount();
        }

        //计算订单费用和实际费用写入订单 (cskaoyan_mall_order)
        // 订单费用 = goods_price + freight_price - coupon_price
        // 实付费用 = order_price - integral_price

        BigDecimal orderPrice = goodsPrice.add(freightPrice).subtract(couponPrice);
        BigDecimal actualPrice = orderPrice.subtract(integralPrice);

        //创建时间和更新时间
        Date date = new Date();

        //初创订单状态为101
        Short orderStatus = 101;

        //自动生成订单编码
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderSn = sdf.format(date);
        orderSn = orderSn.replaceAll("-", "").replaceAll(":","").replaceAll(" ","");
        int newNum = (int)((Math.random()*9+1)*100000);
        newNum += orderStatus  +  (int)((Math.random()*9+1)*532);
        String s = String.valueOf(newNum);
        orderSn += s;

        //用javabean封装订单信息
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(orderSn);
        order.setOrderStatus(orderStatus);
        order.setConsignee(consignee);
        order.setMobile(mobile);
        order.setAddress(address);
        order.setMessage(message);
        order.setGoodsPrice(goodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setGrouponPrice(grouponPrice);
        order.setOrderPrice(orderPrice);
        order.setActualPrice(actualPrice);
        order.setAddTime(date);
        order.setUpdateTime(date);
        order.setDeleted(false);

        //提交订单(cskaoyan_mall_order)
        orderMapper.submitOrder(order);

        //获得新创建的订单ID
        int orderId = orderMapper.selectLastId();
        for (Cart cart : carts) {
            //把购物车中提取出来的商品信息插入到订单商品表中(cskaoyan_mall_order_good)
            Integer goodsId = cart.getGoodsId();
            String goodsSn = cart.getGoodsSn();
            String goodsName = cart.getGoodsName();
            Integer productId = cart.getProductId();
            BigDecimal price = cart.getPrice();
            Integer number = cart.getNumber();
            String[] specifications = cart.getSpecifications();
            String picUrl = cart.getPicUrl();

            OrderGoods orderGoods = new OrderGoods();
            //订单id为上面创建的订单id
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(goodsId);
            orderGoods.setGoodsName(goodsName);
            orderGoods.setGoodsSn(goodsSn);
            orderGoods.setProductId(productId);
            orderGoods.setPrice(price);
            orderGoods.setNumber(number.shortValue());
            orderGoods.setSpecifications(specifications);
            orderGoods.setPicUrl(picUrl);
            orderGoods.setAddTime(date);
            orderGoods.setUpdateTime(date);
            orderGoods.setDeleted(false);
            orderGoodsMapper.submitOrderGoods(orderGoods);
        }

        //返回新创建的订单id
        return orderId;
    }

    @Override
    public int prepay(String orderId) {
        Order order = new Order();
        int id = Integer.parseInt(orderId);
        order.setId(id);
        Date date = new Date();
        //修改 订单更新时间，订单支付时间,订单状态码，订单支付方式
        order.setUpdateTime(date);
        order.setPayTime(date);
        order.setOrderStatus((short)201);
        order.setPayId("1");
        int update = orderMapper.prepay(order);
        return update;
    }

    @Override
    public int refund(String orderId) {
        Order order = new Order();
        int id = Integer.parseInt(orderId);
        order.setId(id);
        Date date = new Date();
        //修改 订单更新时间，订单结束时间,订单状态码
        order.setUpdateTime(date);
        order.setEndTime(date);
        order.setOrderStatus((short)102);
        int update = orderMapper.cancelOrder(order);
        return update;
    }

    @Override
    public int cancel(String orderId) {
        Order order = new Order();
        int id = Integer.parseInt(orderId);
        order.setId(id);
        Date date = new Date();
        //修改 订单更新时间，订单结束时间,订单状态码
        order.setUpdateTime(date);
        order.setEndTime(date);
        order.setOrderStatus((short)102);
        int update = orderMapper.cancelOrder(order);
        return update;
    }

    @Override
    public int confirm(String orderId) {
        Order order = new Order();
        int id = Integer.parseInt(orderId);
        order.setId(id);
        Date date = new Date();
        //修改 订单更新时间，确认收货时间,订单状态码
        order.setUpdateTime(date);
        order.setConfirmTime(date);
        order.setOrderStatus((short)401);
        int update = orderMapper.confirm(order);
        return update;
    }

    @Override
    public int delete(String orderId) {
        Order order = new Order();
        int id = Integer.parseInt(orderId);
        order.setId(id);
        Date date = new Date();
        //修改 订单更新时间，订单结束时间
        order.setEndTime(date);
        order.setUpdateTime(date);
        int delete = orderMapper.delete(order);
        return delete;
    }

}
