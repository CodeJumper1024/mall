package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public int insert(Topic topic) {
        topic.setDeleted(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        topic.setAddTime(format);
        return topicMapper.insert(topic);
    }

    @Override
    public int updateTopic(Topic topic) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        topic.setUpdateTime(format);
        return topicMapper.updateByPrimaryKey(topic);
    }

    @Override
    public List<Topic> queryWxTopics(Integer page, Integer limit) {
        //完成分页的查询
        PageHelper.startPage(page,limit);
        return topicMapper.queryTopicsByPageAndLimit();
    }

    @Override
    public Topic queryTopicById(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Topic> queryTopicByRelatedId() {
        PageHelper.startPage(1,5);
        return topicMapper.queryTopicsByRelatedId();
    }
}
