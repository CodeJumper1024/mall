package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsProductMapper {
    long countByExample(GoodsProductExample example);

    int deleteByExample(GoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsProduct record);

    int insertSelective(GoodsProduct record);

    List<GoodsProduct> selectByExample(GoodsProductExample example);

    GoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByExample(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByPrimaryKeySelective(GoodsProduct record);

    int updateByPrimaryKey(GoodsProduct record);

    List<GoodsProduct> selectByGoodsId(@Param("goodsId") Integer id);

    List<GoodsProduct> selectByGoodsId(@Param("goodsId") int goodsId);

    void updateByProductMap(@Param("product") Map product, @Param("updateTime") String updateTime);

    void insertByProductMap(@Param("product") Map product,@Param("addTime") String addTime,@Param("goodsId") int goodsId);
}
