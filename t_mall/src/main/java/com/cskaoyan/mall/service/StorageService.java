package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;

import java.util.Map;

public interface StorageService {
    public int create(Storage storage);

    public int selectLastId();

    public Map<String,Object> list(int page, int limit, String sort, String order,String key,String name);

    public int deleteStorageById(Integer id);

    public Storage update(Storage storage);
}
