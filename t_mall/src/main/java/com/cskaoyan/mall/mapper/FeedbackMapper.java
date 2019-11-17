package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.FeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackMapper {
    long countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    List<Feedback> queryFeedbacks(@Param("username") String username, @Param("id") Integer id);

    Feedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}