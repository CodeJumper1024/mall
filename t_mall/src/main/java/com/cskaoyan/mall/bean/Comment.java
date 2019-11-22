package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;

    private Integer valueId;

    private Byte type;

    private String content;

    private Integer userId;

    private Boolean hasPicture;

    private String[] picUrls;

    private Short star;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String[] picList;

    private String nickname;

    private String avatar;
}