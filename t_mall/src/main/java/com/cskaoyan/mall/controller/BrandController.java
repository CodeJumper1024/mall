package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/brand/")
public class BrandController {
    @Autowired
    BrandService brandService;
    //查询获取品牌制造商
    @RequestMapping("list")
    public BaseReqVo list(Integer page,Integer limit, String sort, String order,Integer id,String name){
        BaseReqVo baseReqVo = brandService.list(page, limit, sort, order,id,name);
        return baseReqVo;
    }
    @RequestMapping("create")
    public BaseReqVo createBrand(@RequestBody Brand brand){
        BaseReqVo baseReqVo = brandService.createBrand(brand);
        return baseReqVo;
    }
    @RequestMapping("update")
    public BaseReqVo updateBrand(@RequestBody Brand brand){
        BaseReqVo baseReqVo = brandService.updateBrand(brand);
        return baseReqVo;
    }
    @RequestMapping("delete")
    public BaseReqVo deleteBrand(@RequestBody Brand brand){
        BaseReqVo baseReqVo = brandService.deleteBrand(brand);
        return baseReqVo;
    }
}
