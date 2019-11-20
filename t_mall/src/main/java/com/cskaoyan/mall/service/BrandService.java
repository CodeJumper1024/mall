package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Brand;

public interface BrandService {
    BaseReqVo list(Integer page,Integer limit, String sort, String order,Integer id,String name);

    BaseReqVo createBrand(Brand brand);

    BaseReqVo updateBrand(Brand brand);

    BaseReqVo deleteBrand(Brand brand);

    BaseReqVo listWxBrand(Integer page, Integer size);

    BaseReqVo detailWxBrand(Integer id);
}
