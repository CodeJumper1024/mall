package com.cskaoyan.mall.service;
import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> queryAddresses(Integer page, Integer limit, String sort, String order, Integer userId, String name) {
        PageHelper.startPage(page, limit);
        List<Address> addresses = addressMapper.queryAddresses(userId, "%" + name + "%");
        for (Address address : addresses) {
            String province = addressMapper.queryProvinceByPid(address.getProvinceId());
            String city = addressMapper.queryCityByCid(address.getCityId());
            String area = addressMapper.queryAreaByAid(address.getAreaId());
            address.setProvince(province);
            address.setCity(city);
            address.setArea(area);
        }
        return addresses;
    }

    @Override
    public List<Address> queryAddressesByUserId(Integer userId) {
        List<Address> addresses = addressMapper.queryAddressesByUserId(userId);
        for (Address address : addresses) {
            String province = addressMapper.queryProvinceByPid(address.getProvinceId());
            String city = addressMapper.queryCityByCid(address.getCityId());
            String area = addressMapper.queryAreaByAid(address.getAreaId());
            String detailedAddress = province+city+area+" "+address.getAddress();
            address.setDetailedAddress(detailedAddress);
            address.setProvince(province);
            address.setCity(city);
            address.setArea(area);
        }
        return addresses;
    }

    @Override
    public int deleteAddressById(Integer id) {
        return addressMapper.deleteAddressById(id);
    }

    @Override
    public int insertAddress(Address address) {
        Date date = new Date();
        if(address.getId() == 0) {
            address.setAddTime(date);
            address.setDeleted(false);
            if(address.getIsDefault() == true){
                addressMapper.updateIsDefault(address.getUserId());
            }
            return addressMapper.insertSelective(address);
        }else{
            address.setUpdateTime(date);
            if(address.getIsDefault() == true){
                addressMapper.updateIsDefault(address.getUserId());
            }
            return addressMapper.updateByPrimaryKey(address);
        }
    }

    @Override
    public int queryLastId() {
        return addressMapper.queryLastId();
    }

    @Override
    public Address queryAddressesById(Integer id) {
        Address address = addressMapper.selectByPrimaryKey(id);
        String province = addressMapper.queryProvinceByPid(address.getProvinceId());
        String city = addressMapper.queryCityByCid(address.getCityId());
        String area = addressMapper.queryAreaByAid(address.getAreaId());
        String detailedAddress = province+city+area+" "+address.getAddress();
        address.setDetailedAddress(detailedAddress);
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);
        return address;
    }
}
