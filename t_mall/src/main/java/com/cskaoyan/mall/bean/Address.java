package com.cskaoyan.mall.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Address {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer areaId;

    private String areaName;

    private String address;

    private String detailedAddress;

    private String mobile;

    private Boolean isDefault;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String area;

    private String province;

    private String city;
}