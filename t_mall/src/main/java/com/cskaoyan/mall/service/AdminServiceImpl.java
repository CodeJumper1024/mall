package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public BaseReqVo list(int page, int limit, String sort, String order) {

        PageHelper.startPage(page,limit);
        List<Admin> admins = adminMapper.selectAll();
        PageInfo<Admin> objectPageInfo = new PageInfo<>();
        return null;
    }

}
