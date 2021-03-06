package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> queryComments(@Param("userId") Integer userId, @Param("valueId") Integer valueId);

    void deleteComment(@Param("id") Integer id);


    List<Comment> queryCommentsByValueIdAndByType(@Param("valueId") Integer valueId, @Param("type") Integer type, @Param("showType") Integer showType);

    List<Comment> queryCommentsByValueId(@Param("goodsId") Integer id);

    int queryCommentCountByValueId(@Param("goodsId") Integer id);

    int queryCommentsCount(@Param("valueId") Integer valueId, @Param("type") Integer type);

    int queryCommentsHasPicCount(@Param("valueId") Integer valueId, @Param("type") Integer type);

    String queryContent(@Param("commentId") Integer commentId);

    void addComment(@Param("commentId") Integer commentId, @Param("content") String content);

    void commitComment(@Param("goodsId") int goodsId, @Param("content") String content, @Param("star") Integer star,
                       @Param("hasPicture") Boolean hasPicture,
                       @Param("id") Integer id);
}