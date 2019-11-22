package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderSubmitCondition;
import com.cskaoyan.mall.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("wx/order/")
@RestController
public class WxOrderController {

    @Autowired
    WxOrderService wxOrderService;

    @RequestMapping("list")
    public BaseReqVo list(Integer showType,int page,int size){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Map<String,Object> map = wxOrderService.orderList(showType,page,size);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("detail")
    public BaseReqVo detail(Integer orderId){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Map<String, Object> map = wxOrderService.detail(orderId);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("submit")
    public BaseReqVo submit(@RequestBody OrderSubmitCondition orderSubmitCondition){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        //提交订单,返回新创建的订单id
        Integer orderId = wxOrderService.submit(orderSubmitCondition);
        if(orderId != null){
            HashMap<String, Integer> map = new HashMap<>();
            map.put("orderId",orderId);
            baseReqVo.setData(map);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("提交订单成功");
        }
        return baseReqVo;
    }

    @RequestMapping("prepay")
    public BaseReqVo prepay(@RequestBody Map<String,String> map){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        String orderId = map.get("orderId");
        int update = wxOrderService.prepay(orderId);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("支付成功");
        }
        return baseReqVo;
    }

    @RequestMapping("refund")
    public BaseReqVo refund(@RequestBody Map<String,String> map){
        //退款取消订单(待发货阶段)
        BaseReqVo baseReqVo = new BaseReqVo<>();
        String orderId = map.get("orderId");
        int update = wxOrderService.refund(orderId);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("退款成功");
        }
        return baseReqVo;
    }

    @RequestMapping("cancel")
    public BaseReqVo cancel(@RequestBody Map<String,String> map){
        //退款取消订单(待付款阶段)
        BaseReqVo baseReqVo = new BaseReqVo<>();
        String orderId = map.get("orderId");
        int update = wxOrderService.cancel(orderId);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("取消订单成功");
        }
        return baseReqVo;
    }

    @RequestMapping("confirm")
    public BaseReqVo confirm(@RequestBody Map<String,String> map){
        //退款取消订单(待付款阶段)
        BaseReqVo baseReqVo = new BaseReqVo<>();
        String orderId = map.get("orderId");
        int update = wxOrderService.confirm(orderId);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("确认收货成功");
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Map<String,String> map){
        //删除订单(全部订单页面已取消订单，或者待评论页面)
        BaseReqVo baseReqVo = new BaseReqVo<>();
        String orderId = map.get("orderId");
        int update = wxOrderService.delete(orderId);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("删除订单成功");
        }
        return baseReqVo;
    }

    @RequestMapping("goods")
    public BaseReqVo goods(Integer orderId, Integer goodsId) {

        OrderGoods orderGoods = wxOrderService.commentGoods(orderId, goodsId);

        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(orderGoods);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
