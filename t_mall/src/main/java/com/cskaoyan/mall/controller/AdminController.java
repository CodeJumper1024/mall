package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/auth/")
public class AdminController {
    @Autowired
    AdminService adminService;

    //登录
    @RequestMapping("login")
    public String login(@RequestBody Admin admin){
        BaseReqVo baseReqVo = adminService.login(admin);
        return baseReqVo.toString();
    }
}
