package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("list")
    public BaseReqVo List(){
        BaseReqVo baseReqVo = categoryService.list();
        return baseReqVo;
    }
}
