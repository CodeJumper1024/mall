package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsAttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsAttributeMapper {
    long countByExample(GoodsAttributeExample example);

    int deleteByExample(GoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    List<GoodsAttribute> selectByExample(GoodsAttributeExample example);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByExample(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);

    List<GoodsAttribute> selectByGoodsId(@Param("goodsId") Integer id);

    List<GoodsAttribute> selectByGoodsId(@Param("goodsId") int goodsId);

    void updateByAttributeMap(@Param("attribute") Map attribute, @Param("updateTime") String updateTime);

    void deleteByGoodsId(@Param("goodsId") int goodsId);

    void insertByAttributeMap(@Param("attribute") Map attribute,@Param("addTime") String addTime,@Param("goodsId") int goodsId);
}
