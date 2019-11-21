package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.KeyWord;
import com.cskaoyan.mall.service.KeyWordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;

@RestController
@RequestMapping("admin/keyword/")

public class KeyWordController {
    @Autowired
    KeyWordService keyWordService;
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:keyword:list"})
    public BaseReqVo listKeyWord(Integer page, Integer limit, String sort, String order,String keyword,String url){
        BaseReqVo baseReqVo=keyWordService.listKeyWord(page,limit,sort,order,keyword,url);
        return baseReqVo;
    }
    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:keyword:create"})
    public BaseReqVo createKeyWord(@RequestBody KeyWord keyWord){
        System.out.println(keyWord);
        BaseReqVo baseReqVo=keyWordService.createKeyWord(keyWord);
        return baseReqVo;
    }
    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:keyword:update"})
    public BaseReqVo updateKeyWord(@RequestBody KeyWord keyWord){
        BaseReqVo baseReqVo=keyWordService.updateKeyWord(keyWord);
        return baseReqVo;
    }
    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:keyword:delete"})
    public BaseReqVo deleteKeyWord(@RequestBody KeyWord keyWord){
        BaseReqVo baseReqVo=keyWordService.deleteKeyWord(keyWord);
        return baseReqVo;
    }
}
