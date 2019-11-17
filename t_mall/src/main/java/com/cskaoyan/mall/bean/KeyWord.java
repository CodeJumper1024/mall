package com.cskaoyan.mall.bean;

import lombok.Data;

import java.util.Date;

@Data
public class KeyWord {
    private Integer id;
    private String keyword;
    private String url;
    private Boolean isHot;
    private Boolean isDefault;
    private Integer sortOrder;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;
}
