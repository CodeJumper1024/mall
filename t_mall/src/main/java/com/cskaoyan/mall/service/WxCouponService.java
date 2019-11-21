package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;

public interface WxCouponService {
    BaseReqVo mylist(int status, int page, int size);

    BaseReqVo list(int page, int size);

    BaseReqVo receive(int couponId);

    BaseReqVo exchange(String code);
}
