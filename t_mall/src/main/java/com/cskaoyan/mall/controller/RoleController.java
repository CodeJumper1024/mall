package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/role/")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("options")
    public BaseReqVo option(){
        List<Map<String, Object>> list = roleService.selectIdAndName();
        BaseReqVo<List> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(list);
        return baseReqVo;
    }

}
