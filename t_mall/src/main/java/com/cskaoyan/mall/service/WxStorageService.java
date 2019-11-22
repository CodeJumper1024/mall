package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import org.springframework.web.multipart.MultipartFile;

public interface WxStorageService {
    BaseReqVo upload(MultipartFile file);
}
