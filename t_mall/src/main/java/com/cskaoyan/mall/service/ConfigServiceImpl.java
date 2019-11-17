package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    SystemMapper systemMapper;

    System system = new System();

    @Override
    public int updateMall(Mall mall) {
        Date date = new Date();
        system.setUpdateTime(date);
        if(mall.getLitemall_mall_address()!=null && mall.getLitemall_mall_name()!=null &&
                mall.getLitemall_mall_phone()!=null && mall.getLitemall_mall_qq()!=null){
            SystemExample addressExample = new SystemExample();
            addressExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_address");
            system.setKeyValue(mall.getLitemall_mall_address());
            systemMapper.updateByExampleSelective(system,addressExample);

            SystemExample nameExample = new SystemExample();
            nameExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_name");
            system.setKeyValue(mall.getLitemall_mall_name());
            systemMapper.updateByExampleSelective(system,nameExample);

            SystemExample phoneExample = new SystemExample();
            phoneExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_phone");
            system.setKeyValue(mall.getLitemall_mall_phone());
            systemMapper.updateByExampleSelective(system,phoneExample);

            SystemExample qqExample = new SystemExample();
            qqExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_qq");
            system.setKeyValue(mall.getLitemall_mall_qq());
            systemMapper.updateByExampleSelective(system,qqExample);
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Mall queryMall() {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameLike("cskaoyan_mall_mall%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        Mall mall = new Mall();
        for(System system : systemList){
            if("cskaoyan_mall_mall_address".equals(system.getKeyName())){
                mall.setLitemall_mall_address(system.getKeyValue());
            }if("cskaoyan_mall_mall_name".equals(system.getKeyName())){
                mall.setLitemall_mall_name(system.getKeyValue());
            }if("cskaoyan_mall_mall_phone".equals(system.getKeyName())){
                mall.setLitemall_mall_phone(system.getKeyValue());
            }if("cskaoyan_mall_mall_qq".equals(system.getKeyName())){
                mall.setLitemall_mall_qq(system.getKeyValue());
            }
        }
        return mall;
    }

    @Override
    public int updateExpress(Express express) {
        Date date = new Date();
        system.setUpdateTime(date);
        if(express.getLitemall_express_freight_min() != null &&express.getLitemall_express_freight_value() !=null){
            SystemExample minExample = new SystemExample();
            minExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_min");
            system.setKeyValue(express.getLitemall_express_freight_min());
            systemMapper.updateByExampleSelective(system,minExample);

            SystemExample valueExample = new SystemExample();
            valueExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
            system.setKeyValue(express.getLitemall_express_freight_value());
            systemMapper.updateByExampleSelective(system,valueExample);

            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Express queryExpress() {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameLike("cskaoyan_mall_express_freight%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        Express express = new Express();
        for(System system : systemList){
            if("cskaoyan_mall_express_freight_min".equals(system.getKeyName())){
                express.setLitemall_express_freight_min(system.getKeyValue());
            }if("cskaoyan_mall_express_freight_value".equals(system.getKeyName())){
                express.setLitemall_express_freight_value(system.getKeyValue());
            }
        }
        return express;
    }

    @Override
    public int updateOrderz(Orderz orderz) {
        Date date = new Date();
        system.setUpdateTime(date);
        if(orderz.getLitemall_order_comment() != null &&orderz.getLitemall_order_unconfirm() !=null
                && orderz.getLitemall_order_unpaid()!=null){
            SystemExample commentExample = new SystemExample();
            commentExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_comment");
            system.setKeyValue(orderz.getLitemall_order_comment());
            systemMapper.updateByExampleSelective(system,commentExample);

            SystemExample unconfirmExample = new SystemExample();
            unconfirmExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_unconfirm");
            system.setKeyValue(orderz.getLitemall_order_unconfirm());
            systemMapper.updateByExampleSelective(system,unconfirmExample);

            SystemExample unpaidExample = new SystemExample();
            unpaidExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_unpaid");
            system.setKeyValue(orderz.getLitemall_order_unpaid());
            systemMapper.updateByExampleSelective(system,unpaidExample);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Orderz queryOrderz() {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameLike("cskaoyan_mall_order%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        Orderz orderz = new Orderz();
        for(System system : systemList){
            if("cskaoyan_mall_order_unconfirm".equals(system.getKeyName())){
                orderz.setLitemall_order_unconfirm(system.getKeyValue());
            }if("cskaoyan_mall_order_comment".equals(system.getKeyName())){
                orderz.setLitemall_order_comment(system.getKeyValue());
            }if("cskaoyan_mall_order_unpaid".equals(system.getKeyName())){
                orderz.setLitemall_order_unpaid(system.getKeyValue());
            }
        }
        return orderz;
    }

    @Override
    public int updateWx(Wx wx) {
        Date date = new Date();
        system.setUpdateTime(date);
        if(wx.getLitemall_wx_catlog_goods()!= null &&wx.getLitemall_wx_catlog_list() !=null
                && wx.getLitemall_wx_index_brand()!=null && wx.getLitemall_wx_index_hot()!=null
                && wx.getLitemall_wx_index_new()!=null && wx.getLitemall_wx_index_topic()!=null
                && wx.getLitemall_wx_share()!=null){

            SystemExample goodsExample = new SystemExample();
            goodsExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_catlog_goods");
            system.setKeyValue(wx.getLitemall_wx_catlog_goods());
            systemMapper.updateByExampleSelective(system,goodsExample);

            SystemExample listExample = new SystemExample();
            listExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_catlog_list");
            system.setKeyValue(wx.getLitemall_wx_catlog_list());
            systemMapper.updateByExampleSelective(system,listExample);

            SystemExample brandExample = new SystemExample();
            brandExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_brand");
            system.setKeyValue(wx.getLitemall_wx_index_brand());
            systemMapper.updateByExampleSelective(system,brandExample);

            SystemExample hotExample = new SystemExample();
            hotExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_hot");
            system.setKeyValue(wx.getLitemall_wx_index_hot());
            systemMapper.updateByExampleSelective(system,hotExample);

            SystemExample newExample = new SystemExample();
            newExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_new");
            system.setKeyValue(wx.getLitemall_wx_index_new());
            systemMapper.updateByExampleSelective(system,newExample);

            SystemExample topicExample = new SystemExample();
            topicExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_topic");
            system.setKeyValue(wx.getLitemall_wx_index_topic());
            systemMapper.updateByExampleSelective(system,topicExample);

            SystemExample shareExample = new SystemExample();
            shareExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_share");
            system.setKeyValue(wx.getLitemall_wx_share());
            systemMapper.updateByExampleSelective(system,shareExample);

            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Wx queryWx() {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameLike("cskaoyan_mall_wx%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        Wx wx = new Wx();
        for (System system : systemList) {
            if ("cskaoyan_mall_wx_index_new".equals(system.getKeyName())) {
                wx.setLitemall_wx_index_new(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_share".equals(system.getKeyName())) {
                wx.setLitemall_wx_share(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_hot".equals(system.getKeyName())) {
                wx.setLitemall_wx_index_hot(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_catlog_goods".equals(system.getKeyName())) {
                wx.setLitemall_wx_catlog_goods(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_catlog_list".equals(system.getKeyName())) {
                wx.setLitemall_wx_catlog_list(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_brand".equals(system.getKeyName())) {
                wx.setLitemall_wx_index_brand(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_topic".equals(system.getKeyName())) {
                wx.setLitemall_wx_index_topic(system.getKeyValue());
            }
        }
        return wx;
    }
}
