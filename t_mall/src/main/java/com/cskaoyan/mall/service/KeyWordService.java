package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.KeyWord;

public interface KeyWordService {
    BaseReqVo listKeyWord(Integer page, Integer limit, String sort, String order, String keyword, String url);

    BaseReqVo createKeyWord(KeyWord keyWord);

    BaseReqVo updateKeyWord(KeyWord keyWord);

    BaseReqVo deleteKeyWord(KeyWord keyWord);
}
