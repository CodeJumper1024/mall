package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.UserInfo;
import com.cskaoyan.mall.bean.WxComment;
import com.cskaoyan.mall.service.CommentServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/comment/")
public class WxCommentController {
    @Autowired
    CommentServive commentServive;
    @RequestMapping("list")
    public BaseReqVo baseReqVo(Integer valueId,Integer type,Integer size,Integer page,Integer showType){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        List<Comment> comments = commentServive.queryCommentsByValueIdAndByType(valueId,type,showType);
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarUrl("123");
        userInfo.setNickname("123");
        ArrayList<WxComment> wxComments = new ArrayList<>();
        for (Comment comment : comments) {
            WxComment wxComment = new WxComment();
            wxComment.setUserInfo(userInfo);
            wxComment.setAddTime(comment.getAddTime());
            wxComment.setPicList(comment.getPicUrls());
            wxComment.setContent(comment.getContent());
            wxComments.add(wxComment);
        }
        map.put("data",wxComments);
        map.put("count",wxComments.size());
        map.put("currentPage",page);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping("count")
    public BaseReqVo count(Integer valueId,Integer type){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        int allCount = commentServive.queryCommentsCount(valueId,type);
        int hasPicCount = commentServive.queryCommentsHasPicCount(valueId,type);
        map.put("allCount",allCount);
        map.put("hasPicCount",hasPicCount);
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
