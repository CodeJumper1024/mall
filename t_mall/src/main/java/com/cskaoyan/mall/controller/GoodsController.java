package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.GoodsUpdateInfo;
import com.cskaoyan.mall.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/goods/")
public class GoodsController {

    @Autowired
    GoodsService adminGoodsService;

    //分页获取商品
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:goods:list"})
    public BaseReqVo list(Integer page, Integer limit, String sort, String order, String name, Long goodsSn){
        BaseReqVo baseReqVo = adminGoodsService.list(page, limit, sort, order, name, goodsSn);
        return baseReqVo;
    }

    //获取商品详情
    @RequestMapping("detail")
    @RequiresPermissions(value = {"admin:goods:detail"})
    public BaseReqVo detail(int id){
        BaseReqVo baseReqVo = adminGoodsService.detail(id);
        return baseReqVo;
    }

    //获取所有分类和品牌信息
    @RequestMapping("catAndBrand")
    @RequiresPermissions(value = {"admin:goods:catAndBrand"})
    public BaseReqVo catAndBrand(){
        BaseReqVo baseReqVo = adminGoodsService.catAndBrand();
        return baseReqVo;
    }

    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:goods:update"})
    public BaseReqVo update(@RequestBody Map<String, Object> info){
        BaseReqVo baseReqVo = adminGoodsService.update(info);
        return baseReqVo;
    }

    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:goods:delete"})
    public BaseReqVo delete(@RequestBody Map<String, Object> info){
        BaseReqVo baseReqVo = adminGoodsService.delete(info);
        return baseReqVo;
    }

    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:goods:create"})
    public BaseReqVo create(@RequestBody Map<String, Object> info){
        BaseReqVo baseReqVo = adminGoodsService.create(info);
        return baseReqVo;
    }
}
