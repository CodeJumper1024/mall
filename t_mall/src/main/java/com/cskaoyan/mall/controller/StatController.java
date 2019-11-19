package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseReqVo userStat() {
        BaseReqVo baseReqVo = statService.userStat();
        return baseReqVo;
    }

    @RequestMapping("goods")
    public BaseReqVo goodsStat() {
        BaseReqVo baseReqVo = statService.goodsStat();
        return baseReqVo;
    }

    @RequestMapping("order")
    public BaseReqVo orderStat() {
        BaseReqVo baseReqVo = statService.orderStat();
        return baseReqVo;
    }
}
