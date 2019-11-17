package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;

    @Override
    public List<Collect> queryCollection(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {
        PageHelper.startPage(page, limit);
        List<Collect> collection = collectMapper.queryCollection(userId, valueId);
        return collection;
    }
}
