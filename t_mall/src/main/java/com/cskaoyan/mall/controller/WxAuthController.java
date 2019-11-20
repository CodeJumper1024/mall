package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserInfo;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("wx/auth/")
public class WxAuthController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseReqVo login(@RequestBody User user1) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        User user = userService.queryUserByUsernameAndPassword(user1.getUsername(),user1.getPassword());
        if(user != null) {
            //token
            //tokenExpire
            UserInfo userInfo = new UserInfo();
            userInfo.setNickname(user.getNickname());
            userInfo.setAvatarUrl(user.getAvatar());
            int i = userService.update(user);
            map.put("token", "f13rpc3su09zf8z9v22jn8i52l1iyi8b");
            map.put("tokenExpire","2019-11-20T06:50:31.464");
            map.put("userInfo",userInfo);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            baseReqVo.setData(map);
        }else {
            baseReqVo.setErrmsg("账号密码不正确");
            baseReqVo.setErrno(700);
        }
        return  baseReqVo;
    }
    @PostMapping("logout")
    public BaseReqVo logout(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return  baseReqVo;
    }
}
