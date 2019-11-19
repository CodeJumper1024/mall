package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;

import java.util.Map;

public interface AdminService {

    public Map<String,Object> list(int page, int limit, String username, String sort, String order);

    public Admin create(Admin admin);

    public int deleteAdminById(Integer id);

    public Admin updateAdmin(Admin admin);

}
