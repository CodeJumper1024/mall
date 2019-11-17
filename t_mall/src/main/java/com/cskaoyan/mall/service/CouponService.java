package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;

import java.util.List;

public interface CouponService {
    List<Coupon> queryCoupons(Integer page, Integer limit, String name, Integer type, Integer status);

    int insert(Coupon coupon);

    int deleteCoupon(Integer id);

    int updateCoupon(Coupon id);

    Coupon selectCouponsById(Integer id);

    List<CouponUser> queryCouponsUser(Integer page, Integer limit, Integer couponId, Integer userId, Integer status);
}
