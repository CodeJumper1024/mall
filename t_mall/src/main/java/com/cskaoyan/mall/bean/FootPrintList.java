package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FootPrintList {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
    private Integer goodsId;
    private String brief;
    private String picUrl;
    private BigDecimal retailPrice;
    private String name;
}
