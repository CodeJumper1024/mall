package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Collect> queryCollection(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {
        PageHelper.startPage(page, limit);
        List<Collect> collection = collectMapper.queryCollection(userId, valueId);
        return collection;
    }

    @Override
    public String addOrDelCollect(Integer valueId, Integer id) {

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String addTime = dateFormat.format(date);

        Collect collect = collectMapper.queryCollect(valueId, id);
        if (collect != null) {
            collectMapper.delCollect(valueId, id);
            return "delete";
        } else {
            collectMapper.addCollect(valueId, id, addTime);
        }
        return "add";
    }

    @Override
    public List<Goods> queryCollect(Integer type, Integer page, Integer size, Integer id) {

        PageHelper.startPage(page, size);

        List<Integer> goodsIdList = collectMapper.queryGoodsIdByUserId(id);
        List<Goods> collectList = new ArrayList<>();
        for (Integer goodsId : goodsIdList) {
            Goods goods = goodsMapper.queryCollection(goodsId);
            collectList.add(goods);
        }
        return collectList;
    }
}
