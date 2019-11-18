package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;

public interface StatService {

    BaseReqVo goodsStat();

    BaseReqVo userStat();

    BaseReqVo orderStat();
}
