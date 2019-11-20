package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    public List<Map<String,Object>> selectIdAndName();

    public Map<String,Object> list(int page, int limit, String name, String sort, String order);

    public int update(Role role);

    public Role create(Role role);

    public int deleteRoleById(Integer id);

}
