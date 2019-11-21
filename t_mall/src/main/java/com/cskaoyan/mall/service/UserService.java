package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryUsers(Integer page, Integer limit, String sort, String order, String username, String mobile);

    User queryUserByUsernameAndPassword(String username, String password);

    int update(User user);

    BaseReqVo login(User user);

    BaseReqVo register(User user);
}
