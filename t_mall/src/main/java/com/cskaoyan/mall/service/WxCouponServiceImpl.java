package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxCouponServiceImpl implements WxCouponService{

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    //我的优惠券
    @Override
    public BaseReqVo mylist(int status, int page, int size) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        PageHelper.startPage(page, size);
        List<Coupon> data = couponMapper.selectByStatusAndUserId(status, user.getId());
        PageInfo<Coupon> pageInfo = new PageInfo<>(data);
        long count = pageInfo.getTotal();
        dataMap.put("data", data);
        dataMap.put("count", count);
        return BaseReqVo.ok(dataMap);
    }

    //优惠券列表
    @Override
    public BaseReqVo list(int page, int size) {
        HashMap<String, Object> dataMap = new HashMap<>();
        PageHelper.startPage(page, size);
        List<Coupon> data = couponMapper.selectAll();
        PageInfo<Coupon> pageInfo = new PageInfo<>(data);
        long count = pageInfo.getTotal();
        dataMap.put("data", data);
        dataMap.put("count", count);
        return BaseReqVo.ok(dataMap);
    }

    //获取优惠券
    @Override
    public BaseReqVo receive(int couponId) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //增加coupon_user条目
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        CouponUser couponUser = new CouponUser();
        couponUser.setUserId(user.getId());
        couponUser.setCouponId(coupon.getId());
        short status = 0;
        couponUser.setStatus(status);
        couponUser.setStartTime(coupon.getStartTime());
        couponUser.setEndTime(coupon.getEndTime());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(date);
        couponUser.setAddTime(addTime);
        couponUser.setDeleted(false);
        couponUserMapper.insert(couponUser);
        return BaseReqVo.ok();
    }

    @Override
    public BaseReqVo exchange(String code) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Coupon coupon = couponMapper.selectByCode(code);
        if (coupon == null){
            baseReqVo.setErrmsg("优惠券不正确");
            baseReqVo.setErrno(742);
        }else {
            Subject subject = SecurityUtils.getSubject();
            User user = (User)subject.getPrincipal();
            CouponUser couponUser = new CouponUser();
            couponUser.setUserId(user.getId());
            couponUser.setCouponId(coupon.getId());
            short status = 0;
            couponUser.setStatus(status);
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime = simpleDateFormat.format(date);
            couponUser.setAddTime(addTime);
            couponUser.setDeleted(false);
            couponUserMapper.insert(couponUser);
            baseReqVo.setErrmsg("领取成功");
            baseReqVo.setErrno(0);
        }
        return baseReqVo;
    }
}
