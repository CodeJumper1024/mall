package com.cskaoyan.mall.service;

import java.util.Map;

public interface LogService {
    Map<String,Object> list(int page, int limit, String name, String sort, String order);
}
