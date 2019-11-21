package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.KeyWord;

import java.util.List;

public interface WxSearchService {

    KeyWord queryDefaultKeyword();

    List<KeyWord> queryHotKeywords();

    List<String> queryHistoryKeywords(Integer id);

    List<String> queryHelpers(String keyword);

    void clearhistory(Integer id);
}
