package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class CheckOut {
    private Integer cartId;
    private Integer addressId;
    private Integer couponId;
    private Integer grouponRulesId;
}
