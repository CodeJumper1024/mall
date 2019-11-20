package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Module;
import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.PermissionService;
import com.cskaoyan.mall.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/role/")
@RequiresPermissions(value = {"admin:role"})
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

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

    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Role role){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Role resultRole = roleService.create(role);
        if(role !=null){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setData(resultRole);
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Role role){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Integer id = role.getId();

        int delete = roleService.deleteRoleById(id);
        if(delete != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    @RequestMapping(value = "permissions", method = {RequestMethod.GET})
    public BaseReqVo permission(int roleId){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        //获得assignedPermissions 当前角色权限列表
        List<String> list1 = permissionService.getPermissionsByRoleId(roleId);
        //获得systemPermissions 系统权限列表
        List<Module> list2 = permissionService.getSystemPermission();
        HashMap<String, Object> map = new HashMap<>();
        map.put("assignedPermissions",list1);
        map.put("systemPermissions",list2);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping(value = "permissions", method = {RequestMethod.POST})
    public BaseReqVo permission(@RequestBody Map<String,Object> map){
        Integer roleId = (Integer) map.get("roleId");
        List<String> permissions = (List<String>) map.get("permissions");
        BaseReqVo baseReqVo = new BaseReqVo<>();
        int update = permissionService.updatePermissions(roleId,permissions);
        if(update != 0){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }
        return baseReqVo;
    }

}
