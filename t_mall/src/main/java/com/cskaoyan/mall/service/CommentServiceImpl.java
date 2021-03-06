package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentServive {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> queryComments(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {
        PageHelper.startPage(page, limit);
        List<Comment> comments = commentMapper.queryComments(userId, valueId);
        return comments;
    }

    @Override
    public void deleteComment(Integer id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> queryCommentsByValueIdAndByType(Integer valueId, Integer type, Integer showType) {
        return commentMapper.queryCommentsByValueIdAndByType(valueId,type,showType);
    }

    @Override
    public int queryCommentsCount(Integer valueId, Integer type) {
        return commentMapper.queryCommentsCount(valueId,type);
    }

    @Override
    public int queryCommentsHasPicCount(Integer valueId, Integer type) {
        return commentMapper.queryCommentsHasPicCount(valueId,type);
    }
}
