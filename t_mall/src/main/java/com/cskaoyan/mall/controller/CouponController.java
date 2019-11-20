package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions(value = {"admin:coupon:list"})
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
    @RequiresPermissions(value = {"admin:coupon:create"})
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
    @RequiresPermissions(value = {"admin:coupon:delete"})
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
    @RequiresPermissions(value = {"admin:coupon:update"})
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
    @RequestMapping("read")
    @RequiresPermissions(value = {"admin:coupon:read"})
    public BaseReqVo read(Integer id){
        Coupon coupon = couponService.selectCouponsById(id);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setData(coupon);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
    @RequestMapping("listuser")
    public BaseReqVo listuser(Integer page, Integer limit,Integer couponId,Integer userId,Integer status){
        List<CouponUser> couponUsers = couponService.queryCouponsUser(page, limit,couponId,userId,status);
        PageInfo<CouponUser> adsPageInfo = new PageInfo<>(couponUsers);
        long total = adsPageInfo.getTotal();
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",couponUsers);
        map.put("total",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }
}
