package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.WxCart;

public interface CartService {
    BaseReqVo addCart(Cart cart);

    BaseReqVo countCart();

    BaseReqVo indexCart();

    BaseReqVo checkedCart(WxCart cart);

    BaseReqVo updateCart(Cart cart);

    BaseReqVo deleteCart(WxCart cart);

    BaseReqVo fastAddCart(Cart cart);
}
