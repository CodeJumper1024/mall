package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Wx;
import com.cskaoyan.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/config")
public class WxController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value="/wx",method = RequestMethod.POST)
    public BaseReqVo updateWx(@RequestBody Wx wx){
        BaseReqVo baseReqVo = new BaseReqVo();
        int num = configService.updateWx(wx);
        if(num == 1){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrmsg("输入非法,请输入大于0的正整数");
            baseReqVo.setErrno(1);
        }
        return baseReqVo;
    }

    @RequestMapping(value="/wx",method = RequestMethod.GET)
    public BaseReqVo queryMall(){
        BaseReqVo baseReqVo = new BaseReqVo();
        Wx wx = configService.queryWx();
        baseReqVo.setData(wx);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
