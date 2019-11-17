package com.cskaoyan.mall.service;

import java.util.Date;

public interface StatService {

    int queryUsersByAddTime();

    Date queryLatestAddTime();
}
