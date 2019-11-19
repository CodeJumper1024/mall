package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return userMapper.queryUserByUsernameAndPassword(username,password);
    }

    @Override
    public int update(User user) {
        Date date = new Date();
        user.setLastLoginTime(date);
        return userMapper.updateByPrimaryKey(user);
    }
}
