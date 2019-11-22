package com.cskaoyan.mall.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cskaoyan.mall.aopAnnotation.Security;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.InfoData;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.ActionMapper;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.AuthService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.mall.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/auth/")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    ActionMapper actionMapper;

    //登录
    @Security
    @RequestMapping("login")
    public BaseReqVo login(@RequestBody Admin admin){
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (admin.getUsername() == null){
            baseReqVo.setErrmsg("参数不对");
            baseReqVo.setErrno(401);
            return baseReqVo;
        }
        Subject subject = SecurityUtils.getSubject();
        String password = Md5Utils.getMd5(admin.getPassword());
        CustomToken token = new CustomToken(admin.getUsername(), password, "admin");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseReqVo.fail();
        }
        baseReqVo = BaseReqVo.ok();
        String sessionId = subject.getSession().getId().toString();
        baseReqVo.setData(sessionId);
        return baseReqVo;
    }

    //显示
    @RequestMapping("info")
    public BaseReqVo info(String token){
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin)subject.getPrincipal();
        //获取该用户所有的权限及该用户所有的角色名字
        int[] roleIds = admin.getRoleIds();
        List<String> perms = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        for (int roleId : roleIds) {
            Role role = roleMapper.selectById(roleId);
            roles.add(role.getName());
            List<String> apis = actionMapper.selectApiByRoleId(roleId);
            perms.addAll(apis);
        }
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        InfoData data = new InfoData();
        data.setAvatar(admin.getAvatar());
        data.setName(admin.getUsername());
        data.setPerms(perms);
        data.setRoles(roles);
        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("logout")
    public BaseReqVo logout(){
        SecurityUtils.getSubject().logout();
        return BaseReqVo.ok();
    }
}
