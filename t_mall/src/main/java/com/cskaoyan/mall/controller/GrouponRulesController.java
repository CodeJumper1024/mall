package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.GrouponRulesService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/groupon/")
public class GrouponRulesController {
    @Autowired
    GrouponRulesService grouponRulesService;
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:groupon:list"})
    public BaseReqVo list(Integer page, Integer limit, Integer goodsId){
        List<GrouponRules> grouponRules = grouponRulesService.queryGrouponRulers(page, limit,goodsId);
        PageInfo<GrouponRules> adsPageInfo = new PageInfo<>(grouponRules);
        long total = adsPageInfo.getTotal();
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",grouponRules);
        map.put("total",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }
    @PostMapping(value = "delete")
    @RequiresPermissions(value = {"admin:groupon:delete"})
    public BaseReqVo delete(@RequestBody GrouponRules grouponRules){
        int result = grouponRulesService.deleteGroupRules(grouponRules.getId());
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "update")
    @RequiresPermissions(value = {"admin:groupon:update"})
    public BaseReqVo update(@RequestBody GrouponRules grouponRules){
        int result = grouponRulesService.updateGrouponRules(grouponRules);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "create")
    @RequiresPermissions(value = {"admin:groupon:create"})
    public BaseReqVo create(@RequestBody GrouponRules grouponRules){
        int result = grouponRulesService.insertGrouponRules(grouponRules);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }else if(result == 2){
            baseReqVo.setErrno(1004);
            baseReqVo.setErrmsg("商品ID错误");
        }
        return baseReqVo;
    }
//    @RequestMapping("listRecord")
//    public BaseReqVo listRecord(Integer page, Integer limit){
//        List<Groupon> groupon = grouponRulesService.queryGroupon(page, limit);
//        PageInfo<GrouponRules> adsPageInfo = new PageInfo<>(groupon);
//        long total = adsPageInfo.getTotal();
//        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("items",groupon);
//        map.put("total",total);
//        mapBaseReqVo.setData(map);
//        mapBaseReqVo.setErrmsg("成功");
//        mapBaseReqVo.setErrno(0);
//        return mapBaseReqVo;
//    }
}
