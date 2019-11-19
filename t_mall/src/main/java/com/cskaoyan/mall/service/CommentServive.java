package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public interface CommentServive {
    List<Comment> queryComments(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);

    void deleteComment(Integer id);

    List<Comment> queryCommentsByValueIdAndByType(Integer valueId, Integer type, Integer showType);

    int queryCommentsCount(Integer valueId, Integer type);

    int queryCommentsHasPicCount(Integer valueId, Integer type);
}
