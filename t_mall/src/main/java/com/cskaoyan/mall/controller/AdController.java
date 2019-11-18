package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.AdService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/ad/")
public class AdController {
    @Autowired
    AdService adService;
    @RequestMapping(value = "list")
    public BaseReqVo list(Integer page, Integer limit,String name,String content){
        List<Ad> ads = adService.queryAds(page, limit,name,content);
        PageInfo<Ad> adsPageInfo = new PageInfo<>(ads);
        long total = adsPageInfo.getTotal();
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",ads);
        map.put("total",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }
    @PostMapping(value = "delete")
    public BaseReqVo delete(@RequestBody Ad ad){
        int result = adService.deleteAd(ad.getId());
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "create")
    public BaseReqVo create(@RequestBody Ad ad){
        int result = adService.insertAd(ad);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
    @PostMapping(value = "update")
    public BaseReqVo update(@RequestBody Ad ad){
        int result = adService.updateAd(ad);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
}
