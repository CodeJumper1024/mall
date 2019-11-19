package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx/brand/")
@RestController
public class WxBrandController {
    @Autowired
    BrandService brandService;
    @RequestMapping("list")
    public BaseReqVo listWxBrand(Integer page,Integer size) {
        BaseReqVo baseReqVo = brandService.listWxBrand(page, size);
        return baseReqVo;
    }
    @RequestMapping("detail")
    public BaseReqVo detailWxBrand(Integer id) {
        BaseReqVo baseReqVo = brandService.detailWxBrand(id);
        return baseReqVo;
    }

}
