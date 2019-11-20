package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/catalog/")
public class WxCatalogController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("index")
    public BaseReqVo index(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        List<Category> categoryList = categoryService.queryCategoryByLv1();
        Category currentCategory = categoryList.get(0);
        List<Category> currentSubCategory = categoryService.queryCategoryByLv1Id(currentCategory.getId());
        map.put("categoryList",categoryList);
        map.put("currentCategory",currentCategory);
        map.put("currentSubCategory",currentSubCategory);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping("current")
    public BaseReqVo current(Integer id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        Category currentCategory = categoryService.queryCategoryById(id);
        List<Category> currentSubCategory = categoryService.queryCategoryByLv1Id(currentCategory.getId());
        map.put("currentCategory",currentCategory);
        map.put("currentSubCategory",currentSubCategory);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;

    }
}
