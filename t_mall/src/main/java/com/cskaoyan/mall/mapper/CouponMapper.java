package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> queryAdsByNameAndTypeAndStatus(@Param("name") String name, @Param("type") Integer type, @Param("status") Integer status);

    int delete(Integer id);

    int update(Coupon coupon);

    List<Coupon> selectByStatusAndUserId(@Param("status") int status, @Param("userId") Integer userId);

    List<Coupon> selectAll();

    Coupon selectByCode(@Param("code") String code);
}
