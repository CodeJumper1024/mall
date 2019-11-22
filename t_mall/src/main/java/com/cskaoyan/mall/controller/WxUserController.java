package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserOrder;
import com.cskaoyan.mall.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @Autowired
    OrderService orderService;
    @RequestMapping("index")
    public BaseReqVo index(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> ordermap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if(user == null){
            ordermap.put("uncomment",0);
            ordermap.put("unpaid",0);
            ordermap.put("unrecv",0);
            ordermap.put("unship",0);
        }else{
            int uncomment =orderService.queryOrderByUserIdAndStatus(user.getId(),401);
            int unpaid =orderService.queryOrderByUserIdAndStatus(user.getId(),101);
            int unrecv =orderService.queryOrderByUserIdAndStatus(user.getId(),301);
            int unship =orderService.queryOrderByUserIdAndStatus(user.getId(),201);
            ordermap.put("uncomment",uncomment);
            ordermap.put("unpaid",unpaid);
            ordermap.put("unrecv",unrecv);
            ordermap.put("unship",unship);
        }
        HashMap<String, Object> order = new HashMap<>();
        order.put("order",ordermap);
        baseReqVo.setData(order);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
