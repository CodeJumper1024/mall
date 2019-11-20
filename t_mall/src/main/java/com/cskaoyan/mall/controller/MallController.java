package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Mall;
import com.cskaoyan.mall.service.ConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/config")
@RequiresPermissions(value = {"admin:config"})
public class MallController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value="/mall",method = RequestMethod.POST)
    public BaseReqVo updateMall(@RequestBody Mall mall){
        BaseReqVo baseReqVo = new BaseReqVo();
        int num = configService.updateMall(mall);
        if(num == 0){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }else if(num == 1){
            baseReqVo.setErrmsg("信息不全，更新失败");
            baseReqVo.setErrno(1);
        }else if(num == 2){
            baseReqVo.setErrmsg("电话号码格式有误，请重新输入");
            baseReqVo.setErrno(1);
        }else if(num == 3){
            baseReqVo.setErrmsg("qq号格式有误，请重新输入");
            baseReqVo.setErrno(1);
        }
        return baseReqVo;
    }

    @RequestMapping(value="/mall",method = RequestMethod.GET)
    public BaseReqVo queryMall(){
        BaseReqVo baseReqVo = new BaseReqVo();
        Mall mall = configService.queryMall();
        baseReqVo.setData(mall);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

}
