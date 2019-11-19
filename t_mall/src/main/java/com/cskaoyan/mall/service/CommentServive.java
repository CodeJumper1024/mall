package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public interface CommentServive {
    List<Comment> queryComments(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);

    void deleteComment(Integer id);
}
