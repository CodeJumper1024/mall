package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.CollectService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiresPermissions(value = {"admin"})
public class CollectController {

    @Autowired
    CollectService collectService;

    @RequestMapping("collect/list")
    public BaseReqVo collectList(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {
        List<Collect> collection = collectService.queryCollection(page, limit, sort, order, userId, valueId);

        PageInfo<Collect> userPageInfo = new PageInfo<>(collection);
        long total = userPageInfo.getTotal();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", collection);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
