package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;
    @Override
    public List<Ad> queryAds(Integer page, Integer limit, String name, String content) {
        PageHelper.startPage(page,limit);
        List<Ad> ads = adMapper.queryAdsByContentAndName(name,content);
        return ads;
    }

    @Override
    public int deleteAd(Integer id) {
        return adMapper.delete(id);
    }
}
