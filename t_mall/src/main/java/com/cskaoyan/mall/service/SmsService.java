package com.cskaoyan.mall.service;

public interface SmsService {
    void sendRegCaptcha(String mobile, String code);
}
