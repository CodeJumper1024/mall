package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.WxCart;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
}
