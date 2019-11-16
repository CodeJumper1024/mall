package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> queryFeedbacks(Integer page, Integer limit, String sort, String order, String username, Integer id);
}
