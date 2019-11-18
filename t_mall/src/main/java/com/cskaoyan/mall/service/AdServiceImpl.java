package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;
    @Override
    public List<Ad> queryAds(Integer page, Integer limit, String name, String content){
        List<Ad> ads = adMapper.queryAdsByContentAndName(name,content);
        return ads;
    }

    @Override
    public int deleteAd(Integer id) {
        return adMapper.delete(id);
    }

    @Override
    public int insertAd(Ad ad) {
        ad.setDeleted(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ad.setAddTime(format);
        return adMapper.insert(ad);
    }

    @Override
    public int updateAd(Ad ad) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ad.setUpdateTime(format);
        return adMapper.updateByPrimaryKey(ad);
    }
}
