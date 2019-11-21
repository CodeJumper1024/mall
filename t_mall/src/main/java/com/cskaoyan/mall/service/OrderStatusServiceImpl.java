package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{
    @Autowired
    OrderStatusMapper orderStatusMapper;
    @Override
    public String selectStatusByStatusId(Short orderStatus) {
        return orderStatusMapper.selectStatusByStatusId(orderStatus);
    }
}
