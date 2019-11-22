package com.cskaoyan.mall.aop;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.utils.AopUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Component
@Aspect
public class OrderAspect {

    @Autowired
    LogMapper logMapper;

    private String admin;
    private String ipAddress;
    private int type = 2;   //订单操作
    private String action;
    private boolean status;
    private String result;

    @Pointcut("@annotation(com.cskaoyan.mall.aopAnnotation.Order)")
    public void orderPointcut(){
    }

    @Before(value = "orderPointcut()")
    public void myBefore(JoinPoint joinPoint){
    }

    @After("orderPointcut()")
    public void myafter(){
    }

    @Around(value = "orderPointcut()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //ip,uri,action,user
        ipAddress = AopUtils.getIpAddress();
        String uri = request.getRequestURI();
        //action可能为null
        action = urlToAction(uri);

        Object proceed = joinPoint.proceed();

        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        admin = principal.getUsername();

        return proceed;
    }

    //获得被代理方法的返回值
    @AfterReturning(value = "orderPointcut()",returning = "baseReqVo")
    public void myafterReturning(Object baseReqVo){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI();

        BaseReqVo reqVo = (BaseReqVo) baseReqVo;
        if("/admin/order/ship".equals(uri)){
            if("发货成功".equals(reqVo.getErrmsg())){
                status = true;
                result = "系统发货成功";
            }
        }

        Log log = new Log();
        log.setAdmin(admin);
        log.setIp(ipAddress);
        log.setType(type);
        log.setAction(action);
        log.setStatus(status);
        log.setResult(result);
        log.setDeleted(false);

        Date date = new Date();
        log.setAddTime(date);
        log.setUpdateTime(date);

        logMapper.insertLogger(log);
    }

    @AfterThrowing(value = "orderPointcut()",throwing = "exceptionz")
    public void myafterThrowing(Throwable exceptionz){
        //System.out.println("afterThrowing:" + exceptionz.getMessage());
    }

    /**
     * 根据uri获得操作动作
     * @param uri
     * @return
     */
    public String urlToAction(String uri) {
        if("/admin/order/ship".equals(uri)){
            return "订单发货";
        }
        return null;
    }

}
