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
    String regex1 = "^[1][34578][0-9]{9}$";
    String regex2 = "[1-9]\\d{4,11}";
    String regex3 = "^\\+?[1-9]\\d*$";

    @Override
    public int updateMall(Mall mall) {
        Date date = new Date();
        String phone = mall.getLitemall_mall_phone();
        String qq = mall.getLitemall_mall_qq();
        String address = mall.getLitemall_mall_address();
        String name =mall.getLitemall_mall_address();
        if(address.length()==0 || name.length()==0 ||phone.length()==0 ||qq.length()==0){
            return 1;
        }else if(!phone.matches(regex1)) {
            return 2;
        }else if(!qq.matches(regex2)){
            return 3;
        }

        system.setUpdateTime(date);
        SystemExample addressExample = new SystemExample();
        addressExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_address");
        system.setKeyValue(address);
        systemMapper.updateByExampleSelective(system,addressExample);

        SystemExample nameExample = new SystemExample();
        nameExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_name");
        system.setKeyValue(name);
        systemMapper.updateByExampleSelective(system,nameExample);

        SystemExample phoneExample = new SystemExample();
        phoneExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_phone");
        system.setKeyValue(phone);
        systemMapper.updateByExampleSelective(system,phoneExample);

        SystemExample qqExample = new SystemExample();
        qqExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_qq");
        system.setKeyValue(qq);
        systemMapper.updateByExampleSelective(system,qqExample);

        return  0 ;
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
        String min = express.getLitemall_express_freight_min();
        String value = express.getLitemall_express_freight_value();
        if(!min.matches(regex3) ||!value.matches(regex3)){
            return 0;
        }

        system.setUpdateTime(date);
        SystemExample minExample = new SystemExample();
        minExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_min");
        system.setKeyValue(min);
        systemMapper.updateByExampleSelective(system,minExample);

        SystemExample valueExample = new SystemExample();
        valueExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
        system.setKeyValue(value);
        systemMapper.updateByExampleSelective(system,valueExample);
        return 1;
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
        String comment = orderz.getLitemall_order_comment();
        String unconfirm = orderz.getLitemall_order_unconfirm();
        String unpaid = orderz.getLitemall_order_unpaid();

        if(!comment.matches(regex3)||!unconfirm.matches(regex3)||!unpaid.matches(regex3)){
            return 0;
        }
        system.setUpdateTime(date);
        SystemExample commentExample = new SystemExample();
        commentExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_comment");
        system.setKeyValue(comment);
        systemMapper.updateByExampleSelective(system,commentExample);

        SystemExample unconfirmExample = new SystemExample();
        unconfirmExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_unconfirm");
        system.setKeyValue(unconfirm);
        systemMapper.updateByExampleSelective(system,unconfirmExample);

        SystemExample unpaidExample = new SystemExample();
        unpaidExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_order_unpaid");
        system.setKeyValue(unpaid);
        systemMapper.updateByExampleSelective(system,unpaidExample);
        return 1;
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
        String goods = wx.getLitemall_wx_catlog_goods();
        String list = wx.getLitemall_wx_catlog_list();
        String brand = wx.getLitemall_wx_index_brand();
        String hot = wx.getLitemall_wx_index_hot();
        String new1 = wx.getLitemall_wx_index_new();
        String topic = wx.getLitemall_wx_index_topic();
        String share = wx.getLitemall_wx_share();
        if(!goods.matches(regex3)||!list.matches(regex3)||!brand.matches(regex3)||!hot.matches(regex3)||
        !new1.matches(regex3)||!topic.matches(regex3)){
            return 0;
        }

        system.setUpdateTime(date);
        SystemExample goodsExample = new SystemExample();
        goodsExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_catlog_goods");
        system.setKeyValue(goods);
        systemMapper.updateByExampleSelective(system,goodsExample);

        SystemExample listExample = new SystemExample();
        listExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_catlog_list");
        system.setKeyValue(list);
        systemMapper.updateByExampleSelective(system,listExample);

        SystemExample brandExample = new SystemExample();
        brandExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_brand");
        system.setKeyValue(brand);
        systemMapper.updateByExampleSelective(system,brandExample);

        SystemExample hotExample = new SystemExample();
        hotExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_hot");
        system.setKeyValue(hot);
        systemMapper.updateByExampleSelective(system,hotExample);

        SystemExample newExample = new SystemExample();
        newExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_new");
        system.setKeyValue(new1);
        systemMapper.updateByExampleSelective(system,newExample);

        SystemExample topicExample = new SystemExample();
        topicExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_index_topic");
        system.setKeyValue(topic);
        systemMapper.updateByExampleSelective(system,topicExample);

        SystemExample shareExample = new SystemExample();
        shareExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_wx_share");
        system.setKeyValue(share);
        systemMapper.updateByExampleSelective(system,shareExample);

        return 1;
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
