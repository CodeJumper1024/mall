package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.SmsService;
import com.cskaoyan.mall.service.UserService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("wx/auth/")
@Data
public class WxAuthController {
    @Autowired
    UserService userService;
    @Autowired
    SmsService smsService;
    HashMap<String ,Object> wxCode = new HashMap<>();
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
    // 验证码
    @PostMapping("regCaptcha")
    public BaseReqVo regCaptcha(@RequestBody User user){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        String mobile = user.getMobile();
        int randomNo = (int) (Math.random()*1000000);
        String code = String.valueOf(randomNo);
        if(mobile != null){
            wxCode.put(mobile,code);
            smsService.sendRegCaptcha(mobile,code);
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    // 注册
    @PostMapping("register")
    public BaseReqVo register(@RequestBody User user){
        BaseReqVo baseReqVo = userService.register(user,wxCode);
        return baseReqVo;
    }
    // 修改密码
    @PostMapping("reset")
    public BaseReqVo reset(@RequestBody User user){
        BaseReqVo baseReqVo = userService.reset(user,wxCode);
        return baseReqVo;
    }
}
