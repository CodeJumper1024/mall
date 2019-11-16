package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.TopicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/topic/")
public class TopicController {
    @Autowired
    TopicService topicService;
    @RequestMapping("list")
    public BaseReqVo list(Integer page, Integer limit, String title, String subtitle){
        List<Topic> topics = topicService.queryTopics(page, limit,title,subtitle);
        PageInfo<Topic> adsPageInfo = new PageInfo<>(topics);
        long total = adsPageInfo.getTotal();
        BaseReqVo<HashMap<String,Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",topics);
        map.put("total",total);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        mapBaseReqVo.setErrno(0);
        return mapBaseReqVo;
    }
    @PostMapping(value = "delete")
    public BaseReqVo delete(@RequestBody Topic topic){
        int result = topicService.deleteTopic(topic.getId());
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if(result == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
}
