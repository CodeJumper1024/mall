package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/coupon/")
public class CouponController {
    @Autowired
    CouponService couponService;
    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer limit, String name, Integer type,Integer status){
        List<Coupon> coupons = couponService.queryCoupons(page, limit,name,type,status);
        PageInfo<Coupon> adsPageInfo = new PageInfo<>(coupons);
        long total = adsPageInfo.getTotal();
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",coupons);
        map.put("total",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }
    @PostMapping(value = "create")
    public BaseReqVo create(@RequestBody Coupon coupon){
        int result = couponService.insert(coupon);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setData(coupon);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "delete")
    public BaseReqVo delete(@RequestBody Coupon coupon){
        int result = couponService.deleteCoupon(coupon.getId());
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "update")
    public BaseReqVo update(@RequestBody Coupon coupon){
        int result = couponService.updateCoupon(coupon);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setData(coupon);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
}
