package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdminGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/goods/")
public class AdminGoodsController {

    @Autowired
    AdminGoodsService adminGoodsService;

    //分页获取商品
    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer limit, String sort, String order){
        BaseReqVo baseReqVo = adminGoodsService.list(page, limit, sort, order);
        return baseReqVo;
    }
}
