package com.cskaoyan.mall.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

public class CustomRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> originRealms = this.getRealms();
        CustomToken token = (CustomToken)authenticationToken;
        //获取请求token的类型
        String type = token.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        //遍历所有realm,找到处理此type的realm
        for (Realm originRealm : originRealms) {
            if (originRealm.getName().toLowerCase().contains(type)){
                realms.add(originRealm);
            }
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) : this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
