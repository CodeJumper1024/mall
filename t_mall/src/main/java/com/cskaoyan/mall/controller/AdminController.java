package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/admin/")
public class AdminController {

    @Autowired
    AdminService adminAdminService;

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String username, String sort, String order){
        Map<String,Object> map = adminAdminService.list(page, limit, username, sort, order);
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

}
