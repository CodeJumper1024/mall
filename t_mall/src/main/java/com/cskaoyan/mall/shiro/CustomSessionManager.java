package com.cskaoyan.mall.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURL().toString();
        String sessionId = null;
        if (url.contains("wx")){
            sessionId = httpServletRequest.getHeader("X-cskaoyanmall-Admin-Token");
        }else {
            sessionId = httpServletRequest.getHeader("X-Litemall-Admin-Token");
        }
        if (sessionId != null && !"".equals(sessionId)){
            return sessionId;
        }
        return super.getSessionId(request, response);
    }
}
