package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdminService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/admin/")
@RequiresPermissions(value = {"admin:admin"})
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String username, String sort, String order){
        Map<String,Object> map = adminService.list(page, limit, username, sort, order);
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Admin admin){
        BaseReqVo baseReqVo = new BaseReqVo();
        //创建管理员
        Admin resultAdmin = adminService.create(admin);
        if(resultAdmin != null){
            //创建管理员成功
            baseReqVo.setErrno(0);
            baseReqVo.setData(resultAdmin);
            baseReqVo.setErrmsg("成功");
        }else{
            //创建管理员失败
            baseReqVo.setErrno(602);
            baseReqVo.setErrmsg("管理员已经存在");
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Admin admin){
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer id = admin.getId();
        int delete = adminService.deleteAdminById(id);
        if(delete == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Admin admin){
        BaseReqVo baseReqVo = new BaseReqVo();
        Admin resultAdmin = adminService.updateAdmin(admin);
        if(resultAdmin != null){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setData(resultAdmin);
        }
        return baseReqVo;
    }

}
