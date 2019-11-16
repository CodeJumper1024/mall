package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService{
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Override
    public List<GrouponRules> queryGrouponRulers(Integer page, Integer limit, Integer goodsId) {
        PageHelper.startPage(page,limit);
        return grouponRulesMapper.queryGrouponRulersByGoodsId(goodsId);
    }

    @Override
    public int deleteGroupRules(Integer id) {
        return grouponRulesMapper.delete(id);
    }
}
