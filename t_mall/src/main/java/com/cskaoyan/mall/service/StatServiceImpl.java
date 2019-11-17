package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Date queryLatestAddTime() {
        Date latestAddTime = userMapper.queryLatestAddTime();
        return latestAddTime;
    }

    @Override
    public int queryUsersByAddTime() {
        Date latestAddTime = userMapper.queryLatestAddTime();
        int users = userMapper.queryUsersByAddTime(latestAddTime);
        return users;
    }
}
