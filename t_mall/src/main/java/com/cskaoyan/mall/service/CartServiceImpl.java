package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper productMapper;
    @Autowired
    CartMapper cartMapper;
    @Override
    public BaseReqVo addCart(Cart cart) {
        BaseReqVo baseReqVo=new BaseReqVo();
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        GoodsProduct product = productMapper.selectByPrimaryKey(cart.getProductId());
        cart.setUserId(1);
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
        int sum = cartMapper.selectSumNumber(1);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(sum);
        return baseReqVo;
    }

    @Override
    public BaseReqVo indexCart() {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Cart> cartList= cartMapper.selectByUserId(1);
        int goodsCount=0;
        int goodsAmount =0;
        if(!cartList.isEmpty()){
            goodsCount = cartMapper.selectSumNumber(1);
            goodsAmount =cartMapper.selectSumPrice(1);
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
        for (Integer productId : productIds) {
            Cart cart1 = cartMapper.selectByIdAndProductId(productId,1);
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
        Integer[] productIds=cart.getProductIds();
        for (Integer productId : productIds) {
            cartMapper.deleteCart(productId,1);
        }
        baseReqVo=indexCart();
        return baseReqVo;
    }
}
