package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Orderz;
import com.cskaoyan.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/config")
public class OrderzController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value="/order",method = RequestMethod.POST)
    public BaseReqVo updateOrder(@RequestBody Orderz orderz){
        BaseReqVo baseReqVo = new BaseReqVo();
        int num = configService.updateOrderz(orderz);
        if(num == 1){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }else{
            baseReqVo.setErrmsg("输入非法,请输入大于0的正整数");
            baseReqVo.setErrno(1);
        }
        return baseReqVo;
    }

    @RequestMapping(value="/order",method = RequestMethod.GET)
    public BaseReqVo queryMall(){
        BaseReqVo baseReqVo = new BaseReqVo();
        Orderz orderz = configService.queryOrderz();
        baseReqVo.setData(orderz);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
