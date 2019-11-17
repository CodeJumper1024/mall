package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<String, Object> list(int page, int limit, String name, String sort, String order) {
        //分页查询
        PageHelper.startPage(page,limit);
        List<Role> list = new ArrayList<>();
        int total = 0;
        if(name == null){
            list = roleMapper.selectAll();
            total = roleMapper.selectCountId();
        }else{
            //name!=null时 进行模糊查询
            list = roleMapper.selectByNameLike("%" + name +"%");
            total = roleMapper.selectCountIdByNameLike("%" + name +"%");
        }
        //分页处理
        PageInfo<Role> objectPageInfo = new PageInfo<>(list);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",list);
        return map;
    }

    @Override
    public int update(Role role) {
        Date date = new Date();
        role.setUpdateTime(date);
        return roleMapper.update(role);
    }
}
