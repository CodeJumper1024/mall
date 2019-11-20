package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.DashboardService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping("admin/dashboard")
    @RequiresPermissions(value = {"admin:dashboard"})
    public BaseReqVo dashboard(GoodsExample goodsExample, UserExample userExample,
                               GoodsProductExample goodsProductExample, OrderExample orderExample) {

        long goodsTotal = dashboardService.queryGoodsTotal(goodsExample);
        long userTotal = dashboardService.queryUserTotal(userExample);
        long productTotal = dashboardService.queryProductTotal(goodsProductExample);
        long orderTotal = dashboardService.queryOrderTotal(orderExample);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("goodsTotal", goodsTotal);
        dataMap.put("userTotal", userTotal);
        dataMap.put("productTotal", productTotal);
        dataMap.put("orderTotal", orderTotal);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
