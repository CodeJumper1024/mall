package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Express;
import com.cskaoyan.mall.bean.Mall;
import com.cskaoyan.mall.bean.Orderz;
import com.cskaoyan.mall.bean.Wx;

public interface ConfigService {
    int updateMall(Mall mall);

    Mall queryMall();

    int updateExpress(Express express);

    Express queryExpress();

    int updateOrderz(Orderz orderz);

    Orderz queryOrderz();

    int updateWx(Wx wx);

    Wx queryWx();
}
