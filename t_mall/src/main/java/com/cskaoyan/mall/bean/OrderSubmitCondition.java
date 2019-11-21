package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class OrderSubmitCondition {

    private Integer addressId;

    private Integer cartId;

    private Integer couponId;

    private Integer grouponLinkId;

    private Integer grouponRulesId;

    private String message;

}
