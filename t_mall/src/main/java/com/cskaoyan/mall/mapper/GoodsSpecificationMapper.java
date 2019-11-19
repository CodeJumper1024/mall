package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsSpecification;
import com.cskaoyan.mall.bean.GoodsSpecificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsSpecificationMapper {
    long countByExample(GoodsSpecificationExample example);

    int deleteByExample(GoodsSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    List<GoodsSpecification> selectByExample(GoodsSpecificationExample example);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByExample(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    List<GoodsSpecification> selectByGoodsId(@Param("goodsId") int goodsId);

    void updateBySpecificationMap(@Param("specification") Map specification, @Param("updateTime") String updateTime);

    void deleteByGoodsId(@Param("goodsId") int goodsId);

    void insertBySpecificationMap(@Param("specification") Map specification, @Param("addTime") String addTime, @Param("goodsId") int goodsId);

    List<String> querySpecNamesByGoodsId(@Param("goodsId") Integer goodsId);

    List<GoodsSpecification> querySpecValue(@Param("goodsId") Integer goodsId, @Param("name") String name);
}
