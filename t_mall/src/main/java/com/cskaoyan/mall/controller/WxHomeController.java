package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.WxHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx/home/")
@RestController
public class WxHomeController {

    @Autowired
    WxHomeService wxHomeService;

    @RequestMapping("index")
    public BaseReqVo index(){
        BaseReqVo baseReqVo = wxHomeService.index();
        return baseReqVo;
    }
}
