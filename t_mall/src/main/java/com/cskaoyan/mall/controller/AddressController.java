package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AddressService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiresPermissions(value = {"admin"})
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("address/list")
    public BaseReqVo addressList(Integer page, Integer limit, String sort, String order, Integer userId, String name) {
        List<Address> addresses = addressService.queryAddresses(page, limit, sort, order, userId, name);

        PageInfo<Address> userPageInfo = new PageInfo<>(addresses);
        long total = userPageInfo.getTotal();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", addresses);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
