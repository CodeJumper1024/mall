package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> queryCollection(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);
}
