package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public List<Topic> queryTopics(Integer page, Integer limit, String title, String subtitle) {
        PageHelper.startPage(page,limit);
        return topicMapper.queryTopicsByTitleAndBySubtitle(title,subtitle);
    }

    @Override
    public int deleteTopic(Integer id) {
        return topicMapper.delete(id);
    }
}
