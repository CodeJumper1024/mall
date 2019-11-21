package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
