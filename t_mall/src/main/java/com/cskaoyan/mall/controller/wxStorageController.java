package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("wx/storage/")
public class wxStorageController {

    @Autowired
    WxStorageService wxStorageService;

    @RequestMapping("upload")
    public BaseReqVo upload(MultipartFile file){
        BaseReqVo baseReqVo = wxStorageService.upload(file);
        return baseReqVo;
    }
}
