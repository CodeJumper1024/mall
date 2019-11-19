package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Module;
import com.cskaoyan.mall.bean.Permission;

import java.util.List;

public interface PermissionService {
    List<String> getPermissionsByRoleId(int roleId);
    List<Module> getSystemPermission();
}
