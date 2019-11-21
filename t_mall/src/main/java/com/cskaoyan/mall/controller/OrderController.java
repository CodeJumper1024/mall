package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.OrderService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/order/")
@RequiresPermissions(value = {"admin:order"})
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequiresPermissions(value = {"admin:order:list"})
    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer limit, String sort, String order,Integer[] orderStatusArray,Integer userId,String orderSn){

        BaseReqVo baseReqVo = orderService.list(page, limit, sort, order,orderStatusArray,userId,orderSn);
        return baseReqVo;
    }
    @RequestMapping("detail")
    @RequiresPermissions(value = {"admin:order:read"})
    public BaseReqVo detailOrder(Integer id){

        BaseReqVo baseReqVo = orderService.detailOrder(id);
        return baseReqVo;
    }

    @RequestMapping("ship")
    @RequiresPermissions(value = {"admin:order:ship"})
    public BaseReqVo ship(@RequestBody Map<String,Object> map){
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer orderId = (Integer) map.get("orderId");
        String shipChannel = (String) map.get("shipChannel");
        String shipSn = (String) map.get("shipSn");
        int update = orderService.ship(orderId,shipChannel,shipSn);
        if(update !=0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("发货成功");
        }
        return baseReqVo;
    }

}
