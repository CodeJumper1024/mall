package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> queryFeedbacks(Integer page, Integer limit, String sort, String order, String username, Integer id) {
        PageHelper.startPage(page, limit);
        List<Feedback> feedbacks = feedbackMapper.queryFeedbacks("%" + username + "%", id);
        return feedbacks;
    }
}
