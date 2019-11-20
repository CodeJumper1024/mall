package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.UserOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @RequestMapping("index")
    public BaseReqVo index(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        UserOrder userOrder = new UserOrder();
        userOrder.setUncomment(0);
        userOrder.setUnpaid(0);
        userOrder.setUnrecv(0);
        userOrder.setUnship(0);
        baseReqVo.setData(userOrder);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
