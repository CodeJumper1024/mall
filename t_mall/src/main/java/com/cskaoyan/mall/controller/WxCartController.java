package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx/brand/")
@RestController
public class WxCartController {
    @Autowired
    CartService cartService;
}
