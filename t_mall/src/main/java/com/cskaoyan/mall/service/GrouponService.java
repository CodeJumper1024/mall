package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Groupon;

import java.util.List;

public interface GrouponService {

    Integer selectGrouponMemberByRuleId(Integer rulesId);

    //登入用户为团购发起者
    List<Groupon> selectGrouponByUId(Integer creatorUserId);

    //登入用户为团购参与者
    List<Groupon> selectGrouponByCUId(Integer creatorUserId);

    //获取参团人数
    Integer selectCountByGrouponId(Integer grouponId);

    Groupon selectGrouponById(Integer id);

    List<Groupon> selectGrouponByGrouponId(Integer grouponId);

    int selectGrouponCountByCUId(Integer userId);

    int selectGrouponCountByUId(Integer userId);
}
