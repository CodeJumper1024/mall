package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class OrderStatInfo {

    double amount;

    int orders;

    int customers;

    String day;

    double pcr;
}
