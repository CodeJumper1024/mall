package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserInfo;
import com.cskaoyan.mall.service.SmsService;
import com.cskaoyan.mall.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("wx/auth/")
public class WxAuthController {
    @Autowired
    UserService userService;
    @Autowired
    SmsService smsService;
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("regCaptcha",code);
        if(mobile != null){
            smsService.sendRegCaptcha(mobile,code);
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @PostMapping("register")
    public BaseReqVo register(@RequestBody User user){
//        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
//        HashMap<String, Object> map = new HashMap<>();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setNickname(user.getUsername());
//        userInfo.setAvatarUrl(user.getAvatar());
//        map.put("token",123);
//        Calendar instance = Calendar.getInstance();
//        Date date = new Date();
//        instance.setTime(date);
//        instance.add(Calendar.HOUR_OF_DAY, 12);
//        Date time = instance.getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String tokenExpire = format.format(time);
//        map.put("tokenExpire", tokenExpire);
//        map.put("userInfo",userInfo);
//        baseReqVo.setData(map);
//        baseReqVo.setErrno(0);
//        baseReqVo.setErrmsg("成功");
        BaseReqVo baseReqVo = userService.register(user);
        return baseReqVo;
    }
}
