package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.TopicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/topic")
public class WxTopicController {
    @Autowired
    TopicService topicService;

    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer size){
        List<Topic> topicList = topicService.queryWxTopics(page,size);
        //查询所有的条目数
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topicList);
        long total = topicPageInfo.getTotal();

        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",topicList);
        map.put("count",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }

    @RequestMapping("detail")
    public BaseReqVo topic(Integer id){
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        Topic topic = topicService.queryTopicById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("topic",topic);
        map.put("goods",topic.getGoods());
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }

    @RequestMapping("related")
    public BaseReqVo relatedList(Integer id){
        List<Topic> topics = topicService.queryTopicByRelatedId();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(topics);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
