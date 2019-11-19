package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.CommentServive;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/comment")
public class CommentController {

    @Autowired
    CommentServive commentServive;

    @RequestMapping("list")
    public BaseReqVo commentList(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {

        List<Comment> comments = commentServive.queryComments(page, limit, sort, order, userId, valueId);

        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        long total = commentPageInfo.getTotal();

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", comments);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }

    @PostMapping("delete")
    public BaseReqVo commentDelete(@RequestBody Comment comment) {
        commentServive.deleteComment(comment.getId());
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
