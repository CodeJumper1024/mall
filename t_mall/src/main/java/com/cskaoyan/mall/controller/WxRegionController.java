package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx/region/")
@RestController
public class WxRegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    public BaseReqVo listRegin(Integer pid){
        BaseReqVo baseReqVo = regionService.listRegion(pid);
        return baseReqVo;
    }
}
