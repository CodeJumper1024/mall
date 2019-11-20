package com.cskaoyan.mall.exception;

import com.cskaoyan.mall.bean.BaseReqVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public BaseReqVo noPermission(){
        BaseReqVo fail = BaseReqVo.fail();
        fail.setErrmsg("没有权限");
        return fail;
    }
}
