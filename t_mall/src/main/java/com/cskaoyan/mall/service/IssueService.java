package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Issue;
import org.springframework.stereotype.Service;

public interface IssueService {
    BaseReqVo list(Integer page, Integer limit, String sort, String order, String question);

    BaseReqVo createIssue(Issue issue);

    BaseReqVo updateIssue(Issue issue);

    BaseReqVo deleteIssue(Issue issue);
}
