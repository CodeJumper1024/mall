package com.cskaoyan.mall.utils;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Component
public class AopUtils {

    @Autowired
    LogMapper logMapper;

    public static String getIpAddress(){
        InetAddress ip = null;
        String ipAddress = null;
        //获取request 封装ip地址
        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if(servletRequestAttributes != null){
            request = servletRequestAttributes.getRequest();
            String internetIp = request.getHeader("x-forwarded-for");
            if(internetIp == null || internetIp.length() == 0 || "unknown".equalsIgnoreCase(internetIp)){
                internetIp = request.getRemoteAddr();
                if(internetIp.equals("127.0.0.1") || internetIp.equals("0:0:0:0:0:0:0:1")){
                    //根据网卡取本机配置的ip
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ip = inet;
                    ipAddress = ip.toString();
                }
            }
        }
        ipAddress = ipAddress.substring(ipAddress.indexOf("/") + 1);
        return ipAddress;
    }

}
