package com.cskaoyan.mall.shiro;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomToken token = (CustomToken) authenticationToken;
        String username = token.getUsername();
        //数据库查找该用户的信息
        User user = userMapper.selectUserByName(username);
        String passwordFromDb = user.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, passwordFromDb, getName());
        return authenticationInfo;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        String username = primaryPrincipal.getUsername();
        //查表获取当前用户的权限
        //List<String> permissions =  UserMapper.selectPermissionByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }
}
