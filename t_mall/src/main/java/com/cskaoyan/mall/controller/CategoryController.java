package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:category:list"})
    public BaseReqVo List(){
        BaseReqVo baseReqVo = categoryService.list();
        return baseReqVo;
    }
    @RequestMapping("l1")
    @RequiresPermissions(value = {"admin:category:read"})
    public BaseReqVo listFirst(){
        BaseReqVo baseReqVo = categoryService.listFirst();
        return baseReqVo;
    }


    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:category:create"})
    public BaseReqVo createCategory(@RequestBody Category category){
        BaseReqVo baseReqVo = categoryService.createCategory(category);
        return baseReqVo;
    }
    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:category:update"})
    public BaseReqVo updateCategory(@RequestBody Category category){
        BaseReqVo baseReqVo = categoryService.updateCategory(category);
        return baseReqVo;
    }
    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:category:delete"})
    public BaseReqVo deleteCategory(@RequestBody Category category){
        BaseReqVo baseReqVo = categoryService.deleteCategory(category);
        return baseReqVo;
    }

}
