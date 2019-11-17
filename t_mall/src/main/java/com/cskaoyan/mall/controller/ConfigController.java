package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Mall;
import com.cskaoyan.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value="/mall",method = RequestMethod.POST)
    public BaseReqVo updateMall(@RequestBody Mall mall){
        BaseReqVo baseReqVo = new BaseReqVo();
        int num = configService.updateMall(mall);
        if(num == 1){
            baseReqVo.setErrmsg("成功");
        }else{
            baseReqVo.setErrmsg("更新信息不全，更新失败");
        }
        baseReqVo.setErrno(0);
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
