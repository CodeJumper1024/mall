package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserInfo;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;

@RestController
@RequestMapping("wx/auth/")
public class WxAuthController {
    @Autowired
    UserService userService;
    //登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseReqVo login(@RequestBody User user) {
        BaseReqVo baseReqVo = userService.login(user);
        return baseReqVo;
    }

    //登出
    @PostMapping("logout")
    public BaseReqVo logout(){
        Subject subject = SecurityUtils.getSubject();
        User principal = (User)subject.getPrincipal();
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return  baseReqVo;
    }
}
