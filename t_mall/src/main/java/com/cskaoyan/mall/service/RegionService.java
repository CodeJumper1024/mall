package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Region;

public interface RegionService {
    BaseReqVo list();

    BaseReqVo listRegion(Integer pid);
}
