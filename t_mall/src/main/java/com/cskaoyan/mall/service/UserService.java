package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<User> queryUsers(Integer page, Integer limit, String sort, String order, String username, String mobile);

    int update(User user);

    BaseReqVo login(User user);

    BaseReqVo register(User user, HashMap<String, Object> wxCode);

    BaseReqVo reset(User user, HashMap<String, Object> wxCode);

    User queryUserByUserId(Integer creatorUserId);


}
