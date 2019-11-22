package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.aopAnnotation.Security;
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.mall.service.CartService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("wx/cart/")
@RestController
public class WxCartController {
    @Autowired
    CartService cartService;
    @RequestMapping("add")
    public BaseReqVo addCart(@RequestBody Cart cart){
        BaseReqVo baseReqVo=cartService.addCart(cart);
        return baseReqVo;
    }
    @RequestMapping("fastadd")
    public BaseReqVo fastAddCart(@RequestBody Cart cart){
        BaseReqVo baseReqVo=cartService.fastAddCart(cart);
        return baseReqVo;
    }
    @RequestMapping("goodscount")
    public BaseReqVo countCart(){
        BaseReqVo baseReqVo=cartService.countCart();
        return baseReqVo;
    }
    @RequestMapping("index")
    public BaseReqVo indexCart(){
        BaseReqVo baseReqVo=cartService.indexCart();
        return baseReqVo;
    }
    @RequestMapping("checked")
    public BaseReqVo checkedCart(@RequestBody WxCart cart){
        BaseReqVo baseReqVo=cartService.checkedCart(cart);
        return baseReqVo;
    }
    @RequestMapping("update")
    public BaseReqVo updateCart(@RequestBody Cart cart){
        BaseReqVo baseReqVo=cartService.updateCart(cart);
        return baseReqVo;
    }
    @RequestMapping("delete")
    public BaseReqVo deleteCart(@RequestBody WxCart cart){
        BaseReqVo baseReqVo=cartService.deleteCart(cart);
        return baseReqVo;
    }
    @RequestMapping("checkout")
    public BaseReqVo checkoutCart(Integer cartId,Integer addressId,Integer couponId,Integer grouponRulesId){
        CheckOut checkOut=new CheckOut();
        checkOut.setCartId(cartId);
        checkOut.setAddressId(addressId);
        checkOut.setCouponId(couponId);
        checkOut.setGrouponRulesId(grouponRulesId);
        BaseReqVo baseReqVo=cartService.checkoutCart(checkOut);
        return baseReqVo;
    }
}
