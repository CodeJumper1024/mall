package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public interface CollectService {
    List<Collect> queryCollection(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);

    String addOrDelCollect(Integer valueId, Integer id);

    List<Goods> queryCollect(Integer type, Integer page, Integer size, Integer id);
}
