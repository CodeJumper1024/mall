package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Address;

import java.util.List;

public interface AddressService {
    List<Address> queryAddresses(Integer page, Integer limit, String sort, String order, Integer userId, String name);
}
