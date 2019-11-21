package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Id;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.AddressService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("wx/address")
public class WxAddressController {
    @Autowired
    AddressService addressService;
    @RequestMapping("list")
    public BaseReqVo list(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Address> addresses = addressService.queryAddressesByUserId(user.getId());
        baseReqVo.setData(addresses);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseReqVo list(@RequestBody Id id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int i = addressService.deleteAddressById(id.getId());
        if(i == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public BaseReqVo save(@RequestBody Address address){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        address.setUserId(user.getId());
        int i = addressService.insertAddress(address);
        int id = addressService.queryLastId();
        baseReqVo.setData(id);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping("detail")
    public BaseReqVo detail(Integer id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Address address = addressService.queryAddressesById(id);
        baseReqVo.setData(address);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
