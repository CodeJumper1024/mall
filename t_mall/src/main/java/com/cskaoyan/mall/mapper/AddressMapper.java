package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.AddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    List<Address> queryAddresses(@Param("userId") Integer userId, @Param("name") String name);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    String queryProvinceByPid(@Param("provinceId") Integer provinceId);

    String queryCityByCid(@Param("cityId") Integer cityId);

    String queryAreaByAid(@Param("areaId") Integer areaId);

    List<Address> queryAddressesByUserId(@Param("userId") Integer userId);

    int deleteAddressById(@Param("id") Integer id);

    int queryLastId();
}