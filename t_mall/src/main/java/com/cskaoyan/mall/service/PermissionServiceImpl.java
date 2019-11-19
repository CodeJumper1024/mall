package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Module;
import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissionsByRoleId(int roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }

    @Override
    public List<Module> getSystemPermission() {
        return permissionMapper.selectSystemPermission();
    }
}
