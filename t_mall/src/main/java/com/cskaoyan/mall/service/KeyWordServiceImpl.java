package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.KeyWord;
import com.cskaoyan.mall.mapper.KeyWordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyWordServiceImpl implements KeyWordService {
    @Autowired
    KeyWordMapper keyWordMapper;

    @Override
    public BaseReqVo listKeyWord(Integer page, Integer limit, String sort, String order, String keyword, String url) {
        BaseReqVo baseReqVo=new BaseReqVo();
        //分页工具
        PageHelper.startPage(page, limit);
        List<KeyWord> keyWords=null;
        keyWords = keyWordMapper.selectBykeywordAndurl(keyword,url);
        PageInfo<KeyWord> keyWordPageInfo= new PageInfo<>(keyWords);
        long total = keyWordPageInfo.getTotal();
        Map<String ,Object> issueMap=new HashMap<>();
        issueMap.put("total",total);
        issueMap.put("items",keyWords);
        baseReqVo.setData(issueMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public BaseReqVo createKeyWord(KeyWord keyWord) {
        BaseReqVo baseReqVo=new BaseReqVo();
        keyWordMapper.insertKeyWord(keyWord);
        int id=keyWord.getId();
        keyWord = keyWordMapper.selectByPrimaryKey(id);
        baseReqVo.setData(keyWord);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @Override
    public BaseReqVo updateKeyWord(KeyWord keyWord) {
        BaseReqVo baseReqVo=new BaseReqVo();
        keyWordMapper.updateKeyWordById(keyWord);
        int id=keyWord.getId();
        keyWord = keyWordMapper.selectByPrimaryKey(id);
        baseReqVo.setData(keyWord);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @Override
    public BaseReqVo deleteKeyWord(KeyWord keyWord) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int result=keyWordMapper.deleteById(keyWord.getId());
        if(result>0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setErrno(1000);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }
}
