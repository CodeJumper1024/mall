package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> querySearchHistory(Integer page, Integer limit, String sort, String order, Integer userId, String keyword) {
        PageHelper.startPage(page, limit);
        List<SearchHistory> searchHistories = searchHistoryMapper.querySearchHistory(userId, "%" + keyword + "%");
        return searchHistories;
    }
}
