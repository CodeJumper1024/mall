package com.cskaoyan.mall.shiro;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.mapper.AdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminMapper adminMapper;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Admin admin = adminMapper.selectAdminByName(username);
        String passwordFromDb = "admin123";
        //String passwordFromDb = admin.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, passwordFromDb, getName());
        return authenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin primaryPrincipal = (Admin) principalCollection.getPrimaryPrincipal();
        String username = primaryPrincipal.getUsername();
        //查表获取当前用户的权限
        //List<String> permissions =  adminMapper.selectPermissionByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addStringPermission("auth:info");
        return authorizationInfo;
    }
}
