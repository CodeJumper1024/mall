package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.service.SearchHistoryService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiresPermissions(value = {"admin"})
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("history/list")
    public BaseReqVo historyList(Integer page, Integer limit, String sort, String order, Integer userId, String keyword) {

        List<SearchHistory> searchHistories = searchHistoryService.querySearchHistory(page, limit, sort, order, userId, keyword);

        PageInfo<SearchHistory> userPageInfo = new PageInfo<>(searchHistories);
        long total = userPageInfo.getTotal();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", searchHistories);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
