package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Map<String,Object> list(int page, int limit, String username, String sort, String order) {
        //分页查询
        List<Admin> admins = null;
        PageHelper.startPage(page,limit);
        if(username !=null){
            //传入的参数username!=null 要进行模糊查询
            admins = adminMapper.selectByNameLike("%" + username +"%");
        } else{
            //username为null 不用进行模糊查询 显示所有管理员
            admins = adminMapper.selectAll();
        }
        PageInfo<Admin> objectPageInfo = new PageInfo<>(admins);

        HashMap<String, Object> dataMap = new HashMap<>();
        //查询管理员数目
        int total = 0;
        if(username == null){
            //显示所有管理员
            total = adminMapper.selectCountId();
        }else{
            //username !=null 模糊查询
            total = adminMapper.selectCountIdByNameLike("%" + username +"%");
        }
        dataMap.put("total",total);
        dataMap.put("items",admins);

        return dataMap;
    }

    @Override
    public Admin create(Admin admin) {

        //先判断管理员是否已经存在，如果已经存在直接返回null,不用创建了
        int i = adminMapper.queryAdminByName(admin.getUsername());
        if(i!=0){
            return null;
        }
        //更新时间，向数据库写入新创建的管理员信息
        Date date = new Date();
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        int insert = adminMapper.insertAdmin(admin);
        if(insert == 1){
            //找出最后插入的ID
            int id = adminMapper.selectLastId();
            //根据id查找管理员
            Admin resultAdmin = adminMapper.selectById(id);
            return resultAdmin;
        }
        //向数据库写入管理员信息失败
        return null;
    }

    @Override
    public int deleteAdminById(Integer id) {
        return adminMapper.deleteAdminById(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        //更新管理员信息,修改更新时间 updateTime
        Date date = new Date();
        admin.setUpdateTime(date);
        int update = adminMapper.updateAdminById(admin);
        //通过id获得管理员信息
        Admin resultAdmin = null;
        if(update ==1){
            resultAdmin = adminMapper.selectById(admin.getId());
        }
        return resultAdmin;
    }

}
