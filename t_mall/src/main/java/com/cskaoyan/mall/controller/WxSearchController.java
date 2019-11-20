package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.KeyWord;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.WxSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {

    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("index")
    public BaseReqVo searchIndex() {

        User user = new User();
        user.setId(1);

        KeyWord defaultKeyword = wxSearchService.queryDefaultKeyword();
        List<KeyWord> hotKeywordList = wxSearchService.queryHotKeywords();

        ArrayList<Map> historyKeyWordList = new ArrayList<>();
        List<String> historyKeywords = wxSearchService.queryHistoryKeywords(user.getId());
        for (String historyKeyword : historyKeywords) {
            HashMap<String, Object> keywordMap = new HashMap<>();
            keywordMap.put("keyword", historyKeyword);
            historyKeyWordList.add(keywordMap);
        }

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("defaultKeyword", defaultKeyword);
        dataMap.put("hotKeywordList", hotKeywordList);
        dataMap.put("historyKeyWordList", historyKeyWordList);

        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("helper")
    public BaseReqVo searchHelper(String keyword) {
        List<String> helperList = wxSearchService.queryHelpers(keyword);
        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(helperList);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
