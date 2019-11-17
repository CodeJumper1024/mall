package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryUsers(Integer page, Integer limit, String sort, String order, String username, String mobile) {
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.queryUsers("%" + username + "%", mobile);
        return users;
    }
}
