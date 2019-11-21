package com.cskaoyan.mall.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActionMapper {
    List<String> selectApiByRoleId(@Param("roleId") int roleId);
}
