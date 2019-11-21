package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class OrderInfo {

    private String consignee;

    private String addres;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date addTime;

    private String orderSn;

    private BigDecimal actualPrice;

    private String mobile;

    private String orderStatusText;

    private BigDecimal goodsPrice;

    private BigDecimal couponPrice;

    private Integer id;

    private BigDecimal freightPrice;

    private Map<String, Boolean> handleOption;

}
