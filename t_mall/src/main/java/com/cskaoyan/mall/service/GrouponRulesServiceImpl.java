package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService{
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<GrouponRules> queryGrouponRulers(Integer page, Integer limit, Integer goodsId) {
        PageHelper.startPage(page,limit);
        return grouponRulesMapper.queryGrouponRulersByGoodsId(goodsId);
    }

    @Override
    public int deleteGroupRules(Integer id) {
        return grouponRulesMapper.delete(id);
    }

    @Override
    public int updateGrouponRules(GrouponRules grouponRules) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        grouponRules.setUpdateTime(format);
        return grouponRulesMapper.updateByPrimaryKey(grouponRules);
    }

    @Override
    public int insertGrouponRules(GrouponRules grouponRules) {
        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        if(goods == null){
            return 2;
        }
        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        grouponRules.setDeleted(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        grouponRules.setAddTime(format);
        return grouponRulesMapper.insert(grouponRules);
    }
}
