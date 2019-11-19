package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Express;
import com.cskaoyan.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/config")
public class ExpressController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value="/express",method = RequestMethod.POST)
    public BaseReqVo updateExpress(@RequestBody Express express){
        BaseReqVo baseReqVo = new BaseReqVo();
        int num = configService.updateExpress(express);
        if(num == 1){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrmsg("输入非法,请输入大于0的正整数");
            baseReqVo.setErrno(1);
        }
        return baseReqVo;
    }

    @RequestMapping(value="/express",method = RequestMethod.GET)
    public BaseReqVo queryMall(){
        BaseReqVo baseReqVo = new BaseReqVo();
        Express express = configService.queryExpress();
        baseReqVo.setData(express);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
