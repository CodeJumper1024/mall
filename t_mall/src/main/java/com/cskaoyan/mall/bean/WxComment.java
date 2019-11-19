package com.cskaoyan.mall.bean;

import lombok.Data;

import java.util.Date;

@Data
public class WxComment {
    UserInfo userInfo;
    Date addTime;
    String[] picList;
    String content;
}
