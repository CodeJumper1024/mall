package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> queryTopics(Integer page, Integer limit, String title, String subtitle);

    int deleteTopic(Integer id);

    int insert(Topic topic);

    int updateTopic(Topic topic);
}
