package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;

import java.util.List;

public interface GrouponRulesService {
    List<GrouponRules> queryGrouponRulers(Integer page, Integer limit, Integer goodsId);

    int deleteGroupRules(Integer id);

    int updateGrouponRules(GrouponRules grouponRules);

    int insertGrouponRules(GrouponRules grouponRules);

<<<<<<< HEAD
    List<Groupon> queryGroupon(Integer page, Integer limit, Integer goodsId);

    GrouponRules queryGrouponRulesById(Integer rulesId);
=======
    List<GrouponRules> queryGrouponsRulesList(Integer page, Integer size);

    GrouponRules selectRulesById(Integer rulesId);
>>>>>>> bf4c3506b96f782c80519cffd50dc08fe50165b5
}
