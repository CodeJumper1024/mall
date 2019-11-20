package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Module;
import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    int deletePermissionByRoleId(@Param("roleId") Integer roleId);

    List<String> selectPermissionsByRoleId (@Param("roleId") int roleId);

    List<Module> selectSystemPermission();

    int insertPermissionsByArray(@Param("roleId") Integer roleId, @Param("list") List<String> permissions, @Param("date")Date date);
}
