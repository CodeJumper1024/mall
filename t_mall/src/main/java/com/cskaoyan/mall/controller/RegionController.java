package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/region/")
public class RegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    public BaseReqVo List(){
        BaseReqVo baseReqVo = regionService.list();
        return baseReqVo;
    }
}
