package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String name, String sort, String order){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Map<String,Object> map = roleService.list(page,limit,name,sort,order);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Role role){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        int update = roleService.update(role);
        if(update != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }


}
