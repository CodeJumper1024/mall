package com.cskaoyan.mall.service;
import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> queryAddresses(Integer page, Integer limit, String sort, String order, Integer userId, String name) {
        PageHelper.startPage(page, limit);
        List<Address> addresses = addressMapper.queryAddresses(userId, "%" + name + "%");
        return addresses;
    }
}
