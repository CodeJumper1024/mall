package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.IssueExample;
import com.cskaoyan.mall.mapper.IssueMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;

    @Override
    public BaseReqVo list(Integer page, Integer limit, String sort, String order, String question) {
        BaseReqVo baseReqVo=new BaseReqVo();
        //分页工具
        PageHelper.startPage(page, limit);
        List<Issue> issues=null;
        issues = issueMapper.selectByQuestion(question);
        PageInfo<Issue> issuePageInfo= new PageInfo<>(issues);
        long total = issuePageInfo.getTotal();
        Map<String ,Object> issueMap=new HashMap<>();
        issueMap.put("total",total);
        issueMap.put("items",issues);
        baseReqVo.setData(issueMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public BaseReqVo createIssue(Issue issue) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int i=issueMapper.insertIssue(issue);
        if(i>0) {
            int id = issue.getId();
            issue = issueMapper.selectByPrimaryKey(id);
            baseReqVo.setData(issue);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }else{
            baseReqVo.setData(issue);
            baseReqVo.setErrno(1001);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo updateIssue(Issue issue) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int i=issueMapper.updateIssueById(issue);
        if(i>0) {
            int id=issue.getId();
            issue = issueMapper.selectByPrimaryKey(id);
            baseReqVo.setData(issue);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setData(issue);
            baseReqVo.setErrno(1002);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo deleteIssue(Issue issue) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int i = issueMapper.deleteByPrimaryKey(issue.getId());
        if(i>0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setData(issue);
            baseReqVo.setErrno(1003);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }
}
