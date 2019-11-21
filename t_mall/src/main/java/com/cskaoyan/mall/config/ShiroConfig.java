package com.cskaoyan.mall.config;

import com.cskaoyan.mall.shiro.AdminRealm;
import com.cskaoyan.mall.shiro.CustomRealmAuthenticator;
import com.cskaoyan.mall.shiro.CustomSessionManager;
import com.cskaoyan.mall.shiro.WxRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    /*shiroFilter*/
    //使用securityManager
    //判断是否通过认证
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //那个Manager拥有的filter
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //认证失败重定向的url
        shiroFilterFactoryBean.setLoginUrl("/admin/redirect");
        //设置过滤哪些url请求
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //不拦截登录，可以匿名访问
        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/auth/info", "anon");
        filterChainDefinitionMap.put("/wx/auth/login", "anon");
        //设置访问此接口需要的权限
        //filterChainDefinitionMap.put("/admin/auth/info", "perms[info]");
        //设置那哪些接口需要权限
        //filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /*SecurityManager*/
    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(AdminRealm adminRealm, WxRealm wxRealm,
                                                            CustomSessionManager sessionManager,
                                                            CustomRealmAuthenticator authenticator){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置shiro管理需要的realm
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        securityManager.setRealms(realms);
        //配置shiro管理需要的sessionManager
        securityManager.setSessionManager(sessionManager);
        securityManager.setAuthenticator(authenticator);
        return securityManager;
    }

    /*声明式鉴权*/
    //使用securityMananger
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager defaultSecurityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultSecurityManager);
        return advisor;
    }

    /*sessionManager获取需要权限处理的请求的sessionId,判断是否通过认证，是否去获取权限*/
    @Bean
    public CustomSessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //删除无效session
        customSessionManager.setDeleteInvalidSessions(true);
        //customSessionManager.setGlobalSessionTimeout(6000000);
        return customSessionManager;
    }

    /*自定义认证器对不同类型的token交给不同的realm处理*/
    /*SercurityManaget成员组件之*/
    @Bean
    public CustomRealmAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm){
        CustomRealmAuthenticator authenticator = new CustomRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        //给Authenticator认证器成员变量realms赋值
        realms.add(adminRealm);
        realms.add(wxRealm);
        authenticator.setRealms(realms);
        return authenticator;
    }
}
