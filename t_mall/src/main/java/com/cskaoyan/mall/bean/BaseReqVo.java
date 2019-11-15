package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class BaseReqVo {
    T data;
    String errmsg;
    int errno;
}
