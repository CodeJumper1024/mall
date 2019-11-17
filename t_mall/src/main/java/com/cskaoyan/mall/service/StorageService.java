package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;

public interface StorageService {
    public int create(Storage storage);

    public int selectLastId();

}
