package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.GrouponRulesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesMapper {
    long countByExample(GrouponRulesExample example);

    int deleteByExample(GrouponRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    List<GrouponRules> selectByExample(GrouponRulesExample example);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByExample(@Param("record") GrouponRules record, @Param("example") GrouponRulesExample example);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> queryGrouponRulersByGoodsId(@Param("goodsId") Integer goodsId);

    int delete(Integer id);

    List<GrouponRules> selectAllRule();

    int selectDiscountById(Integer id);
}
