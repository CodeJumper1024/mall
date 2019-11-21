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
    public int selectgrouponCount() {
        return 0;
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
}
