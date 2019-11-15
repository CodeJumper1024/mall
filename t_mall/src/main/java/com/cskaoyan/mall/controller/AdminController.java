package com.cskaoyan.mall.controller;
import java.util.ArrayList;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.InfoData;
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
    public BaseReqVo login(@RequestBody Admin admin){
        BaseReqVo baseReqVo = adminService.login(admin);
        return baseReqVo;
<<<<<<< HEAD
=======
    }
    //显示
    @RequestMapping("info")
    public BaseReqVo info(String token){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("dongdong");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);
        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
>>>>>>> 5c78a40a5cdf6453aecff3e706a938c5646cd238
    }
}
