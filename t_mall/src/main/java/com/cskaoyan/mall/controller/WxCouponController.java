package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.WxCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/coupon/")
public class WxCouponController {

    @Autowired
    WxCouponService wxCouponService;

    @RequestMapping("mylist")
    public BaseReqVo mylist(int status, int page, int size){
        BaseReqVo baseReqVo = wxCouponService.mylist(status, page, size);
        return baseReqVo;
    }

    @RequestMapping("list")
    public BaseReqVo list(int page, int size){
        BaseReqVo baseReqVo = wxCouponService.list(page, size);
        return baseReqVo;
    }

    @RequestMapping("receive")
    public BaseReqVo reveive(@RequestBody Map map){
        int couponId = (int)map.get("couponId");
        BaseReqVo baseReqVo = wxCouponService.receive(couponId);
        return baseReqVo;
    }

    @RequestMapping("exchange")
    public BaseReqVo exchange(@RequestBody Map map){
        String code = (String) map.get("code");
        BaseReqVo baseReqVo = wxCouponService.exchange(code);
        return baseReqVo;
    }

    @RequestMapping("selectlist")
    public BaseReqVo selectList(int cartId, int grouponRulesId){
        BaseReqVo baseReqVo = wxCouponService.selectList(cartId, grouponRulesId);
        return baseReqVo;
    }
}
