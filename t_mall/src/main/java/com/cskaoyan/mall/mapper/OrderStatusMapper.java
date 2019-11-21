package com.cskaoyan.mall.mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderStatusMapper {
    String selectStatusByStatusId(@Param("orderStatus") Short orderStatus);
}
