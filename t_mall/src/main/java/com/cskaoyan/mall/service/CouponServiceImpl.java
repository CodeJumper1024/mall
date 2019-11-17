package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Override
    public List<Coupon> queryCoupons(Integer page, Integer limit, String name, Integer type, Integer status) {
        PageHelper.startPage(page,limit);
        List<Coupon> coupons = couponMapper.queryAdsByNameAndTypeAndStatus(name,type,status);
        return coupons;
    }

    @Override
    public int insert(Coupon coupon) {
        coupon.setDeleted(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        coupon.setAddTime(format);
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

    @Override
    public Coupon selectCouponsById(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponUser> queryCouponsUser(Integer page, Integer limit, Integer couponId, Integer userId, Integer status) {
        PageHelper.startPage(page,limit);
        List<CouponUser> couponUsers = couponUserMapper.queryCouponsUserByCouponIdAndUserIDAndStatus(couponId,userId,status);
        return couponUsers;
    }
}
