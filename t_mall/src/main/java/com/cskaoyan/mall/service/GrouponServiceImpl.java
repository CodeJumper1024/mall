package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponExample;
import com.cskaoyan.mall.mapper.GrouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService{
    @Autowired
    GrouponMapper grouponMapper;

    @Override
    public Integer selectGrouponMemberByRuleId(Integer rulesId) {
        return grouponMapper.selectGrouponMemberByRuleId(rulesId);
    }

    @Override
    public List<Groupon> selectGrouponByUId(Integer creatorUserId) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andCreatorUserIdEqualTo(creatorUserId);
        return grouponMapper.selectByExample(grouponExample);
    }

    @Override
    public List<Groupon> selectGrouponByCUId(Integer creatorUserId) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andCreatorUserIdNotEqualTo(creatorUserId);
        return grouponMapper.selectByExample(grouponExample);
    }

    @Override
    public Integer selectCountByGrouponId(Integer grouponId) {
        return grouponMapper.selectCountByGrouponId(grouponId);
    }

    @Override
    public Groupon selectGrouponById(Integer id) {
        return grouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Groupon> selectGrouponByGrouponId(Integer grouponId) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andGrouponIdEqualTo(grouponId);
        return grouponMapper.selectByExample(grouponExample);
    }

    @Override
    public int selectGrouponCountByCUId(Integer userId) {
        return grouponMapper.selectGrouponCountByNUId(userId);
    }

    @Override
    public int selectGrouponCountByUId(Integer userId) {
        return grouponMapper.selectGrouponCountByUId(userId);
    }

}
