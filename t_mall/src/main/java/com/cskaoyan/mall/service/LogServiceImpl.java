package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Map<String, Object> list(int page, int limit, String name, String sort, String order) {
        //分页查询
        PageHelper.startPage(page,limit);
        List<Log> list = new ArrayList<>();
        if(name == null){
            list = logMapper.selectAll();
        }else{
            //name!=null时 进行模糊查询
            list = logMapper.selectByNameLike("%" + name +"%");
        }
        PageInfo<Log> logPageInfo = new PageInfo<>(list);
        long total = logPageInfo.getTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",list);
        return map;
    }

}
