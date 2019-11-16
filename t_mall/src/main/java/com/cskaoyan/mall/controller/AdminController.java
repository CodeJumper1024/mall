package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/admin/")
public class AdminController {

    @Autowired
    AdminService adminAdminService;

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String sort, String order){
        BaseReqVo baseReqVo = adminAdminService.list(page, limit, sort, order);
        return baseReqVo;
    }

}
