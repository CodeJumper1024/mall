package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsSpecification;
import com.cskaoyan.mall.bean.GoodsSpecificationExample;
import com.cskaoyan.mall.mapper.GoodsSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService{

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Override
    public List<GoodsSpecification> selectGoodsSpecificationByGoodsId(Integer goodsId) {
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(goodsId);
        return goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
    }
}
