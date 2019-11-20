package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Module;
import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public int updatePermissions(Integer roleId, List<String> permissions) {
        //先把原来的permission数据删除
        int delete = permissionMapper.deletePermissionByRoleId(roleId);
        //然后再插入新的permission数据，注意更新时间update_time
        //如果插入的新的permission数据为空,返回1
        if(permissions.isEmpty()){
            return 1;
        }
        Date date = new Date();
        int insert = permissionMapper.insertPermissionsByArray(roleId,permissions,date);
        return insert;
    }

}
