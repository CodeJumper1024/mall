package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.SearchHistory;

import java.util.List;

public interface SearchHistoryService {
    List<SearchHistory> querySearchHistory(Integer page, Integer limit, String sort, String order, Integer userId, String keyword);
}
