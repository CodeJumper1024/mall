package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.KeyWord;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.KeyWordMapper;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxSearchServiceImpl implements WxSearchService {

    @Autowired
    KeyWordMapper keyWordMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public KeyWord queryDefaultKeyword() {
        KeyWord defaultKeyword = keyWordMapper.queryDefaultKeyword();
        return defaultKeyword;
    }

    @Override
    public List<KeyWord> queryHotKeywords() {
        List<KeyWord> hotKeywordList = keyWordMapper.queryHotKeywords();
        return hotKeywordList;
    }

    @Override
    public List<String> queryHistoryKeywords(Integer id) {
        List<String> historyKeywordList = searchHistoryMapper.queryHistoryKeywords(id);
        return historyKeywordList;
    }

    @Override
    public List<String> queryHelpers(String keyword) {
        List<String> helpers = goodsMapper.queryHelpers("%" + keyword + "%");
        return helpers;
    }

    @Override
    public void clearhistory(Integer id) {
        searchHistoryMapper.clearhistory(id);
    }
}
