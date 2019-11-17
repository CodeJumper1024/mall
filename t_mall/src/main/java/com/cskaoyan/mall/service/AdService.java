package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;

import java.util.List;

public interface AdService {
    List<Ad> queryAds(Integer page, Integer limit, String name, String content);

    int deleteAd(Integer id);
}
