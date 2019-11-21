package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Goods;

import java.util.Map;

public interface GoodsService {
    BaseReqVo list(Integer page, Integer limit, String sort, String order, String name, Long goodsSn);

    BaseReqVo detail(int id);

    BaseReqVo catAndBrand();

    BaseReqVo update(Map<String, Object> info);

    BaseReqVo delete(Map<String, Object> info);

    BaseReqVo create(Map<String, Object> info);

<<<<<<< HEAD

=======
>>>>>>> c7acded77025fd733deab1b2f6a2bd3799df03bb
    Goods queryGoodsByGoodsId(Integer goodsId);

    Goods queryGoods(Integer id);

}
