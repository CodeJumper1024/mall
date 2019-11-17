package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.KeyWord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyWordMapper {
    List<KeyWord> selectBykeywordAndurl(@Param ("keyword") String keyword, @Param("url") String url);

    void insertKeyWord(@Param("keyWord") KeyWord keyWord);

    KeyWord selectByPrimaryKey(@Param("id") int id);

    void updateKeyWordById(@Param("keyWord") KeyWord keyWord);

    int deleteById(@Param("id") Integer id);
}
