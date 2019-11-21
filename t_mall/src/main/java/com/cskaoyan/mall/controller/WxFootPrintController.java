package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.FootprintService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx/footprint/")
@RestController
public class WxFootPrintController {
    @Autowired
    FootprintService footprintService;
    @RequestMapping("list")
    public BaseReqVo listFootPrint(Integer page,Integer size) {
        BaseReqVo baseReqVo = footprintService.listFootPrint(page, size);
        return baseReqVo;
    }
    @RequestMapping("delete")
    public BaseReqVo deleteFootPrint(@RequestBody Footprint footprint){
        BaseReqVo baseReqVo=footprintService.deleteFootPrint(footprint);
        return baseReqVo;
    }

}
