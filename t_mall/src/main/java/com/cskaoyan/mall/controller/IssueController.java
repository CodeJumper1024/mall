package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/issue/")
public class IssueController {
    @Autowired
    IssueService issueService;
    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer limit, String sort, String order,String question){
        BaseReqVo baseReqVo=issueService.list(page,limit,sort,order,question);
        return baseReqVo;

    }
    @RequestMapping("create")
    public BaseReqVo createIssue(@RequestBody Issue issue){
        BaseReqVo baseReqVo=issueService.createIssue(issue);
        return baseReqVo;
    }
    @RequestMapping("update")
    public BaseReqVo updateIssue(@RequestBody Issue issue){
        BaseReqVo baseReqVo=issueService.updateIssue(issue);
        return baseReqVo;
    }
    @RequestMapping("delete")
    public BaseReqVo deleteIssue(@RequestBody Issue issue){
        BaseReqVo baseReqVo=issueService.deleteIssue(issue);
        return baseReqVo;
    }
}
