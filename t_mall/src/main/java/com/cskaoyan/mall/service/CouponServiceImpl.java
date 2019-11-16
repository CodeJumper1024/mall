package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponMapper couponMapper;
    @Override
    public List<Coupon> queryCoupons(Integer page, Integer limit, String name, Integer type, Integer status) {
        PageHelper.startPage(page,limit);
        List<Coupon> coupons = couponMapper.queryAdsByNameAndTypeAndStatus(name,type,status);
        return coupons;
    }

    @Override
    public int insert(Coupon coupon) {
        return couponMapper.insert(coupon);
    }

    @Override
    public int deleteCoupon(Integer id) {
        return couponMapper.delete(id);
    }

    @Override
    public int updateCoupon(Coupon coupon) {
        return couponMapper.update(coupon);
    }
}
