package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> queryUsers(@Param("username") String username, @Param("mobile") String mobile);

    int queryUsersByAddTime(@Param("addTime") Date addTime);

    Date queryLatestAddTime();

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}