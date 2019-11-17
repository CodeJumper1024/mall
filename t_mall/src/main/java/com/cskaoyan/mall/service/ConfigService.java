package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Mall;

public interface ConfigService {
    int updateMall(Mall mall);

    Mall queryMall();
}
