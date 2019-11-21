package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<Footprint> queryFootprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId) {
        PageHelper.startPage(page,limit);
        List<Footprint> footprints = footprintMapper.queryFootprints(userId, goodsId);
        return footprints;
    }

    @Override
    public BaseReqVo listFootPrint(Integer page, Integer size) {
        BaseReqVo baseReqVo=new BaseReqVo();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        //分页工具
        PageHelper.startPage(page,size);
        FootprintExample footprintExample = new FootprintExample();
        footprintExample.createCriteria().andUserIdEqualTo(user.getId()).andDeletedEqualTo(false);
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        List<FootPrintList> footprintList =new ArrayList<>();
        for (Footprint footprint : footprints) {
            FootPrintList footPrintList=new FootPrintList();
            Goods goods=goodsMapper.selectByPrimaryKey(footprint.getGoodsId());
            footPrintList.setId(footprint.getId());
            footPrintList.setAddTime(footprint.getAddTime());
            footPrintList.setGoodsId(goods.getId());
            footPrintList.setBrief(goods.getBrief());
            footPrintList.setPicUrl(goods.getPicUrl());
            footPrintList.setRetailPrice(goods.getRetailPrice());
            footPrintList.setName(goods.getName());
            footprintList.add(footPrintList);
        }
        PageInfo<Footprint> footprintPageInfo=new PageInfo<>(footprints);
        long total = footprintPageInfo.getTotal();
        long totalPages=total/size;
        Map<String,Object> footmap=new HashMap<>();
        footmap.put("totalPages",totalPages);
        footmap.put("footprintList",footprintList);
        baseReqVo.setData(footmap);
        baseReqVo.setErrmsg("显示成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public void insertFoot(Integer userId, Integer goodsId) {
        Footprint footprint=new Footprint();
        footprint.setUserId(userId);
        footprint.setGoodsId(goodsId);
        footprintMapper.insert(footprint);
    }

    @Override
    public BaseReqVo deleteFootPrint(Footprint footprint) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int i = footprintMapper.deleteByid(footprint.getId());
        if(i>0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setErrno(1003);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }
}
