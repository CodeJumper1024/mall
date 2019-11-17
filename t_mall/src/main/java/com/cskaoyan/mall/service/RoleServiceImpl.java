package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Map<String, Object>> selectIdAndName() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Role> roles = roleMapper.allRole();
        for (Role role : roles) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value",role.getId());
            map.put("label",role.getName());
            list.add(map);
        }
        return list;
    }
}
