package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/log/")
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String name, String sort, String order){
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String,Object> map = logService.list(page,limit,name,sort,order);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        return baseReqVo;
    }
}
