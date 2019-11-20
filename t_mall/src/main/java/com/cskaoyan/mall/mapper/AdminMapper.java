package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminExample;
import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectByUsernameAndPassword(@Param("admin") Admin admin);

    List<Admin> selectAll();

    List<Admin> selectByNameLike(@PathParam("username") String username);

    int selectCountId();

    int selectCountIdByNameLike(@Param("username") String username);

    int queryAdminByName(@Param("username") String username);

    int insertAdmin(@Param("admin") Admin admin);

    int selectLastId();

    Admin selectById(@Param("id") int id);

    int deleteAdminById(@Param("id") Integer id);

    int updateAdminById(@Param("admin") Admin admin);

    Admin selectById(Integer id);

    Admin selectAdminByName(@Param("username") String username);

    int[] selectRoleIdsByUsername(@Param("username") String username);
}
