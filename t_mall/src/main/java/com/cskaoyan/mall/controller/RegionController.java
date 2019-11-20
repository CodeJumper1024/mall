package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.service.RegionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/region/")
@RequiresPermissions(value = {"admin:region"})
public class RegionController {
    @Autowired
    RegionService regionService;
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:region:list"})
    public BaseReqVo List(){
        BaseReqVo baseReqVo = regionService.list();
        return baseReqVo;
    }
}
