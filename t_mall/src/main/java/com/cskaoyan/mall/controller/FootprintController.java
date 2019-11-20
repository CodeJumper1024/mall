package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.service.FootprintService;
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
public class FootprintController {

    @Autowired
    FootprintService footprintService;

    @RequestMapping("footprint/list")
    public BaseReqVo userList(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId) {
        List<Footprint> footprints = footprintService.queryFootprints(page, limit, sort, order, userId, goodsId);

        PageInfo<Footprint> userPageInfo = new PageInfo<>(footprints);
        long total = userPageInfo.getTotal();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", footprints);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
