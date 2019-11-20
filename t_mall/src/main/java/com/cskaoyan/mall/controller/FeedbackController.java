package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.service.FeedbackService;
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
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("feedback/list")
    public BaseReqVo feedbackList(Integer page, Integer limit, String sort, String order, String username, Integer id) {
        List<Feedback> feedbacks = feedbackService.queryFeedbacks(page, limit, sort, order, username, id);

        PageInfo<Feedback> userPageInfo = new PageInfo<>(feedbacks);
        long total = userPageInfo.getTotal();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", feedbacks);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
