package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public List<Footprint> queryFootprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId) {
        PageHelper.startPage(page,limit);
        List<Footprint> footprints = footprintMapper.queryFootprints(userId, goodsId);
        return footprints;
    }
}
