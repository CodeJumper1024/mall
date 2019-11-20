package com.cskaoyan.mall.controller;
import java.io.Serializable;
import java.util.ArrayList;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.InfoData;
import com.cskaoyan.mall.service.AuthService;
import com.cskaoyan.mall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/auth/")
public class AuthController {
    @Autowired
    AuthService authService;

    //登录
    @RequestMapping("login")
    public BaseReqVo login(@RequestBody Admin admin){
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (admin.getUsername() == null){
            baseReqVo.setErrmsg("参数不对");
            baseReqVo.setErrno(0);
            return baseReqVo;
        }
        Subject subject = SecurityUtils.getSubject();
        CustomToken token = new CustomToken(admin.getUsername(), admin.getPassword(), "admin");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseReqVo.fail();
        }
        baseReqVo = BaseReqVo.ok();
        String sessionId = (String)subject.getSession().getId();
        baseReqVo.setData(sessionId);
        return baseReqVo;
    }

    //显示
    @RequestMapping("info")
    public BaseReqVo info(String token){
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin)subject.getPrincipal();
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
    }

    @RequestMapping("logout")
    public BaseReqVo logout(){
        SecurityUtils.getSubject().logout();
        return BaseReqVo.ok();
    }
}
