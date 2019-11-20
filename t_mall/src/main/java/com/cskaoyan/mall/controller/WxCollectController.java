package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.CollectService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/collect")
public class WxCollectController {

    @Autowired
    CollectService collectService;

    @PostMapping("addordelete")
    public BaseReqVo addOrDelCollect(@RequestBody Collect collect) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        String type = collectService.addOrDelCollect(collect.getValueId(), user.getId());

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", type);

        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("list")
    public BaseReqVo collectList(Integer type, Integer page, Integer size) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<Goods> collectList = collectService.queryCollect(type, page, size, user.getId());

        PageInfo<Goods> collectPageInfo = new PageInfo<>(collectList);
        int totalPages = collectPageInfo.getPages();

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("totalPages", totalPages);
        dataMap.put("collectList", collectList);

        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
