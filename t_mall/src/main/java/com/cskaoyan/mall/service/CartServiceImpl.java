package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.mapper.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper productMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    SystemMapper systemMapper;
    @Override
    public BaseReqVo addCart(Cart cart) {
        BaseReqVo baseReqVo=new BaseReqVo();
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        GoodsProduct product = productMapper.selectByPrimaryKey(cart.getProductId());
        cart.setChecked(false);
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setPrice(product.getPrice());
        cart.setSpecifications(product.getSpecifications());
        cart.setPicUrl(goods.getPicUrl());
        int hasCart=cartMapper.selectHasCart(cart.getGoodsId(),cart.getProductId(),cart.getUserId());
        if(hasCart>0){
            Cart cart1=cartMapper.selectCart(cart.getGoodsId(),cart.getProductId(),cart.getUserId());
            int total=cart.getNumber()+cart1.getNumber();
            cart1.setNumber(total);
            int result1=cartMapper.updateNumber(cart1.getNumber(),cart1.getId());
            if (result1 > 0) {
                int sum = cartMapper.selectSumNumber(cart.getUserId());
                baseReqVo.setErrmsg("update成功");
                baseReqVo.setErrno(0);
                baseReqVo.setData(sum);
            } else {
                baseReqVo.setData(123);
                baseReqVo.setErrno(1001);
                baseReqVo.setErrmsg("失败");
            }
        }else {
            int result = cartMapper.insert(cart);
            if (result > 0) {
                int sum = cartMapper.selectSumNumber(cart.getUserId());
                baseReqVo.setErrmsg("成功");
                baseReqVo.setErrno(0);
                baseReqVo.setData(sum);
            } else {
                baseReqVo.setData(123);
                baseReqVo.setErrno(1001);
                baseReqVo.setErrmsg("失败");
            }
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo countCart() {
        BaseReqVo baseReqVo = new BaseReqVo();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        List<Cart> cartList=null;
        cartList= cartMapper.selectByUserId(user.getId());
        int sum=0;
        if(!cartList.isEmpty()){
            sum = cartMapper.selectSumNumber(user.getId());
        }
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(sum);
        return baseReqVo;
    }

    @Override
    public BaseReqVo indexCart() {
        BaseReqVo baseReqVo = new BaseReqVo();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        List<Cart> cartList= cartMapper.selectByUserId(user.getId());
        int goodsCount=0;
        int goodsAmount =0;
        if(!cartList.isEmpty()){
            goodsCount = cartMapper.selectSumNumber(user.getId());
            goodsAmount =cartMapper.selectSumPrice(user.getId());
        }
        Map<String,Object> cartTotal=new HashMap<>();
        cartTotal.put("goodsCount",goodsCount);
        cartTotal.put("checkedGoodsCount",goodsCount);
        cartTotal.put("goodsAmount",goodsAmount);
        cartTotal.put("checkedGoodsAmount",goodsAmount);
        Map<String,Object> cartMap=new HashMap<>();
        cartMap.put("cartTotal",cartTotal);
        cartMap.put("cartList",cartList);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(cartMap);
        return baseReqVo;
    }

    @Override
    public BaseReqVo checkedCart(WxCart cart) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer[] productIds=cart.getProductIds();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        for (Integer productId : productIds) {
            Cart cart1 = cartMapper.selectByIdAndProductId(productId,user.getId());
            cart1.setChecked(cart.getIsChecked());
            cartMapper.updateCheckById(cart1.getId(),cart1.getChecked());
        }
        baseReqVo=indexCart();
        return baseReqVo;
    }

    @Override
    public BaseReqVo updateCart(Cart cart) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int reult=cartMapper.updateNumber(cart.getNumber(),cart.getId());
        if(reult>0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setErrno(1002);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo deleteCart(WxCart cart) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        Integer[] productIds=cart.getProductIds();
        for (Integer productId : productIds) {
            cartMapper.deleteCart(productId,user.getId());
        }
        baseReqVo=indexCart();
        return baseReqVo;
    }

    @Override
    public BaseReqVo fastAddCart(Cart cart) {
        BaseReqVo baseReqVo=new BaseReqVo();
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        GoodsProduct product = productMapper.selectByPrimaryKey(cart.getProductId());
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();;
        cart.setUserId(user.getId());
        cart.setChecked(true);
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setPrice(product.getPrice());
        cart.setSpecifications(product.getSpecifications());
        cart.setPicUrl(goods.getPicUrl());
        int result = cartMapper.insertAll(cart);
        if (result > 0) {
            int id = cart.getId();
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            baseReqVo.setData(id);
        } else {
            baseReqVo.setData(123);
            baseReqVo.setErrno(1001);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo checkoutCart(CheckOut checkOut) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        List<Cart> checkedGoodsList=new ArrayList<>();
        int number=0;
        int price=0;
        int goodsTotalPrice=0;
        Date date=new Date();
        if(checkOut.getCartId()==0){
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andCheckedEqualTo(true).andDeletedEqualTo(false).andUserIdEqualTo(user.getId());
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            for (Cart cart : carts) {
                number = cart.getNumber();
                price = cart.getPrice().intValue();
                checkedGoodsList.add(cart);
                goodsTotalPrice= number*price+goodsTotalPrice;
            }
        }else {
            Cart cart = cartMapper.selectByPrimaryKey(checkOut.getCartId());
            number = cart.getNumber();
            price = cart.getPrice().intValue();
            date = cart.getAddTime();
            checkedGoodsList.add(cart);
            goodsTotalPrice= number*price;
        }
        Map<String,Object> checkoutMap=new HashMap<>();
        if(checkOut.getGrouponRulesId()==0){
            checkoutMap.put("grouponPrice",0);
            checkoutMap.put("grouponRulesId",0);
        }else{
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(checkOut.getGrouponRulesId());
            checkoutMap.put("grouponPrice",grouponRules.getDiscount());
            checkoutMap.put("grouponRulesId",grouponRules.getId());
        }
        checkoutMap.put("goodsTotalPrice",goodsTotalPrice);
        if(checkOut.getAddressId()==0){
            Address address=addressMapper.selecetByUserIdAndDefault(user.getId(),1);
            checkoutMap.put("checkedAddress",address);
            checkoutMap.put("addressId",address.getId());
        }else{
            Address address=addressMapper.selectByPrimaryKey(checkOut.getAddressId());
            checkoutMap.put("checkedAddress",address);
            checkoutMap.put("addressId",address.getId());
        }
        CouponUserExample couponUserExample = new CouponUserExample();
        couponUserExample.createCriteria().andUserIdEqualTo(user.getId()).andStatusEqualTo((short)0).andDeletedEqualTo(false).andStartTimeLessThanOrEqualTo(date).andEndTimeGreaterThanOrEqualTo(date);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);
        int actualPrice=0;
        if(checkOut.getCouponId()==0||checkOut.getCouponId()==-1){
            actualPrice=0;
            int couponPrice=0;
            int availableCouponLength=0;
            int couponId=0;
            for (CouponUser couponUser : couponUsers) {
                Coupon coupon=couponMapper.selectALL(couponUser.getCouponId());
                if(coupon.getMin().intValue()<goodsTotalPrice){
                    availableCouponLength++;
                }
                if(coupon.getDiscount().intValue()>couponPrice){
                    couponPrice=coupon.getDiscount().intValue();
                    couponId=coupon.getId();
                }
            }
            if(availableCouponLength==0){
                couponPrice=0;
            }
            actualPrice=goodsTotalPrice-couponPrice;
            checkoutMap.put("couponPrice",couponPrice);
            checkoutMap.put("couponId",couponId);
            checkoutMap.put("availableCouponLength",availableCouponLength);
        }else{
            int availableCouponLength=0;
            Coupon coupon=couponMapper.selectALL(checkOut.getCouponId());
            actualPrice=goodsTotalPrice-coupon.getDiscount().intValue();
            checkoutMap.put("couponId",coupon.getId());
            CouponExample couponExample = new CouponExample();
            for (CouponUser couponUser : couponUsers) {
                Coupon coupon1 = couponMapper.selectALL(couponUser.getCouponId());
                if (coupon1.getMin().intValue() < goodsTotalPrice) {
                    availableCouponLength++;
                }
            }
            if(availableCouponLength==0){
                coupon.setDiscount(new BigDecimal(0));
            }
            checkoutMap.put("couponPrice",coupon.getDiscount());
            checkoutMap.put("availableCouponLength",availableCouponLength);

        }
        int freight_min=0;
        freight_min=systemMapper.selectByName("cskaoyan_mall_express_freight_min");
        int freightPrice=systemMapper.selectByName("cskaoyan_mall_express_freight_value");
        if(goodsTotalPrice>freight_min){
            freightPrice=0;
        }
        actualPrice=actualPrice+freightPrice;
        checkoutMap.put("actualPrice",actualPrice);
        checkoutMap.put("orderTotalPrice",actualPrice);
        checkoutMap.put("freightPrice",freightPrice);
        checkoutMap.put("checkedGoodsList",checkedGoodsList);
        BaseReqVo baseReqVo=new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(checkoutMap);
        return baseReqVo;
    }
}
