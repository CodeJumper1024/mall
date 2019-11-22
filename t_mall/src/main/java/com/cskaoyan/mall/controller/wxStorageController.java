package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/storage/")
public class wxStorageController {

    @Autowired
    WxStorageService wxStorageService;

    @RequestMapping("upload")
    public BaseReqVo upload(){
        return null;
    }
}
